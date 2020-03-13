package cn.edu.dgut.school_helper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.edu.dgut.school_helper.config.response.ServiceRuntimeExecption;
import cn.edu.dgut.school_helper.constant.PostConstant;
import cn.edu.dgut.school_helper.mapper.ImageMapper;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Image;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.pojo.dto.PostOutputDTO;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.Base64Utils;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private FastDFSClientWrapper fastDFSClient;

	@Override
	@Transactional
	public CommonResponse addPost(Post post, String[] imageStrs) {
		User user = userMapper.selectByPrimaryKey(post.getOpenId());
		if (user == null) {
			return CommonResponse.error("没有该用户");
		}

		post.setStatus(PostConstant.RELEASE);
		int row = postMapper.insertSelective(post);
		if (row != 1) {
			return CommonResponse.error("插入post失败");
		}
		// 或许有用
		if (imageStrs.length == 0) {
			return CommonResponse.error("该贴子没有图片");
		}

		uploadImages(post.getPostId(), imageStrs);
		return CommonResponse.isOk(row);
	}

	@Override
	@Transactional
	public CommonResponse updatePost(Post post, String[] imageStrs) {
		Post post2 = postMapper.selectByPrimaryKey(post.getPostId());
		if (post2 == null) {
			return CommonResponse.error("没有该帖子");
		}
		if (!StringUtils.equals(post.getOpenId(), post2.getOpenId())) {
			return CommonResponse.error("不是本人的帖子，不可更新");
		}

		post.setStatus(PostConstant.RELEASE);
		int row = postMapper.updateByPrimaryKeySelective(post);
		if (row != 1) {
			return CommonResponse.error("更新帖子失败");
		}
		//不获取新的图片路径
		List<Image> oldLocation = imageMapper.select(new Image().setPostId(post.getPostId()));
		// 插入新的图片
		uploadImages(post.getPostId(), imageStrs);
		// 删除旧的图片
		for (Image image : oldLocation) {
			fastDFSClient.deleteFile(image.getImageUrl());
			imageMapper.deleteByPrimaryKey(image.getImageId());
		}
		return CommonResponse.isOk("更新成功");
	}

	@Override
	@Transactional
	public CommonResponse deletePostById(Post post) {
		Post post2 = postMapper.selectByPrimaryKey(post.getPostId());
		if (post2 == null) {
			return CommonResponse.error("没有该帖子");
		}
		if (!StringUtils.equals(post.getOpenId(), post2.getOpenId())) {
			return CommonResponse.error("不是本人的帖子，不可删除");
		}

		int row = postMapper
				.updateByPrimaryKeySelective(new Post().setPostId(post.getPostId()).setStatus(PostConstant.DELETED));
		// 删除旧的图片
		List<Image> oldLocation = imageMapper.select(new Image().setPostId(post.getPostId()));
		for (Image image : oldLocation) {
			fastDFSClient.deleteFile(image.getImageUrl());
			imageMapper.deleteByPrimaryKey(image.getImageId());
		}

		// 图片就不管了
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectAllPostByOpenId(Post post) {
		return CommonResponse.isOk(postMapper.selectAllPostByOpenId(post.getOpenId()));
	}

	@Override
	public CommonResponse selectPostListPaging(PostQueryDTO postQueryDTO) {
		PageHelper.startPage(postQueryDTO.getPageNum(), postQueryDTO.getPageSize());
		List<PostOutputDTO> posts = postMapper.selectPostListPaging(postQueryDTO.getPostType(),
				postQueryDTO.getGoodsType());
		return CommonResponse.isOk(posts);
	}

	private void uploadImages(Integer postId, String[] imageStrs) {

		List<String> imagesLocation = new ArrayList<>();
		int j =1;
		try {
			for (String imageStr : imageStrs) {
				byte[] bytes = Base64Utils.decode(imageStr);
				imagesLocation.add(fastDFSClient.uploadFile(bytes, bytes.length, "jpg"));
			}
			// 图片位置存入数据库
			for (int i = 0; i < imagesLocation.size(); i++) {
				imageMapper
						.insertSelective(new Image().setImageUrl(imagesLocation.get(i)).setOrder(i+1).setPostId(postId));
			}

		} catch (Exception e) {
			// 删除服务器上的文件
			for (String location : imagesLocation) {
				System.out.println(location);
				fastDFSClient.deleteFile(location);
			}
			// 回滚
//			e.printStackTrace();
			throw new RuntimeException("上传文件错误");
		}
	}

}
