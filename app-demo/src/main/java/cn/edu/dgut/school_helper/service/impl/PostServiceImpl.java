package cn.edu.dgut.school_helper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.dgut.school_helper.constant.PostConstant;
import cn.edu.dgut.school_helper.mapper.ImageMapper;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.pojo.Image;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private FastDFSClientWrapper fastDFSClient;

	@Override
	@Transactional
	public CommonResponse addPost(Post post, List<MultipartFile> images) {
		post.setPostId(PostConstant.RELEASE);
		int row = postMapper.insertSelective(post);
		if (row != 1) {
			return CommonResponse.error("插入post失败");
		}
		//或许有用
		if(images == null || images.size() == 0) {
			return CommonResponse.error("该贴子没有图片");
		}
		uploadImages(post.getPostId(), images);
		return CommonResponse.isOk(row);
	}

	@Override
	@Transactional
	public CommonResponse updatePost(Post post, List<MultipartFile> images) {
		int row = postMapper.updateByPrimaryKeySelective(post);
		if(row != 1) {
			return CommonResponse.error("更新帖子失败");
		}
		//删除旧的图片
		List<Image> oldLocation = imageMapper.select(new Image().setPostId(post.getPostId()));
		for (Image image : oldLocation) {
			fastDFSClient.deleteFile(image.getImageUrl());
			imageMapper.deleteByPrimaryKey(image.getImageId());
		}
		//插入新的图片
		uploadImages(post.getPostId(), images);
		return CommonResponse.error("更新成功");
	}

	@Override
	public CommonResponse deletePostById(Post post) {
		int row = postMapper.updateByPrimaryKeySelective(new Post().setPostId(post.getPostId()).setStatus(PostConstant.DELETED));
		//图片就不管了
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectPostByOpenId(Post post) {
		return CommonResponse.isOk(postMapper.selectAll());
	}

	@Override
	public CommonResponse selectAllPost() {
		return null;
	}

	private void uploadImages(Integer postId,List<MultipartFile> images) {
		
		List<String> imagesLocation = new ArrayList<>();
		try {
			// 上传文件服务器
			for (MultipartFile image : images) {
				imagesLocation.add(fastDFSClient.uploadFile(image.getBytes(), image.getSize(), getExtention(image)));
			}
			// 图片位置存入数据库
			for (int i = 0; i < imagesLocation.size(); i++) {
				imageMapper.insertSelective(
						new Image().setImageUrl(imagesLocation.get(i)).setOrder(i).setPostId(postId));
			}

		} catch (Exception e) {
			// 删除服务器上的文件
			for (String location : imagesLocation) {
				fastDFSClient.deleteFile(location);
			}
			//回滚
			throw new RuntimeException("上传文件错误");
		}
	}
	
	
	private String getExtention(MultipartFile image) {
		String originalFileName = image.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		return extension;
	}
}
