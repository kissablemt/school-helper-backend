package cn.edu.dgut.school_helper.service.impl;

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
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;
import cn.edu.dgut.school_helper.util.JsonResult;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public JsonResult addPost(Post post, String[] imageStrs) {
        User user = userMapper.selectByPrimaryKey(post.getOpenId());
        if (user == null) {
            return JsonResult.errorMsg("没有该用户");
        }

        post.setStatus(PostConstant.RELEASE);
        int row = postMapper.insertSelective(post);
        if (row != 1) {
            return JsonResult.errorMsg("插入post失败");
        }
        // 或许有用
        if (imageStrs.length == 0) {
            return JsonResult.errorMsg("该贴子没有图片");
        }

        uploadImages(post.getPostId(), imageStrs);
        return JsonResult.ok(row);
    }

    @Override
    @Transactional
    public JsonResult updatePost(Post post, String[] imageStrs) {
        Post post2 = postMapper.selectByPrimaryKey(post.getPostId());
        if (post2 == null) {
            return JsonResult.errorMsg("没有该帖子");
        }
        if (!StringUtils.equals(post.getOpenId(), post2.getOpenId())) {
            return JsonResult.errorMsg("不是本人的帖子，不可更新");
        }

        post.setStatus(PostConstant.RELEASE);
        int row = postMapper.updateByPrimaryKeySelective(post);
        if (row != 1) {
            return JsonResult.errorMsg("更新帖子失败");
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
        return JsonResult.ok("更新成功");
    }

    @Override
    @Transactional
    public JsonResult deletePostById(Post post) {
        Post post2 = postMapper.selectByPrimaryKey(post.getPostId());
        if (post2 == null) {
            return JsonResult.errorMsg("没有该帖子");
        }
        if (!StringUtils.equals(post.getOpenId(), post2.getOpenId())) {
            return JsonResult.errorMsg("不是本人的帖子，不可删除");
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
            return JsonResult.ok(row);
        }
        return JsonResult.errorMsg("删除失败");
    }

    @Override
    public JsonResult selectAllPostByOpenId(Post post) {
        return JsonResult.ok(postMapper.selectAllPostByOpenId(post.getOpenId()));
    }

    @Override
    public JsonResult selectPostListPaging(PostQueryDTO postQueryDTO) {
        PageHelper.startPage(postQueryDTO.getPageNum(), postQueryDTO.getPageSize());
        List<PostOutputDTO> posts = postMapper.selectPostListPaging(postQueryDTO.getPostType(),
                postQueryDTO.getGoodsType(), postQueryDTO.getKeyword());
        return JsonResult.ok(posts);
    }

    @Override
    public JsonResult selectSecondHandPostListPaging(PostQueryDTO postQueryDTO) {
        PageHelper.startPage(postQueryDTO.getPageNum(), postQueryDTO.getPageSize());
        List<PostOutputDTO> posts = postMapper.selectSecondHandPostListPaging(postQueryDTO.getKeyword());
        return JsonResult.ok(posts);
    }

    private void uploadImages(Integer postId, String[] imageStrs) {

        List<String> imagesLocation = new ArrayList<>();
        try {
            for (String imageStr : imageStrs) {
                byte[] bytes = Base64Utils.decode(imageStr);
                imagesLocation.add(fastDFSClient.uploadFile(bytes, bytes.length, "jpg"));
            }
            // 图片位置存入数据库
            for (int i = 0; i < imagesLocation.size(); i++) {
                imageMapper
                        .insertSelective(new Image().setImageUrl(imagesLocation.get(i)).setOrder(i + 1).setPostId(postId));
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
