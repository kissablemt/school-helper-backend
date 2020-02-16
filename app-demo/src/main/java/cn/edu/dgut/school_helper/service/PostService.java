package cn.edu.dgut.school_helper.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface PostService{
	
		public CommonResponse selectPostByOpenId(Post post);
		public CommonResponse addPost(Post post, List<MultipartFile> images);
		public CommonResponse updatePost(Post post, List<MultipartFile> images);
		public CommonResponse deletePostById(Post post);
		public CommonResponse selectAllPost();
}




