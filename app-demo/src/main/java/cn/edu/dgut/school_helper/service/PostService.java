package cn.edu.dgut.school_helper.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface PostService{
	
		
		public CommonResponse addPost(Post post, List<MultipartFile> images);
		public CommonResponse updatePost(Post post, List<MultipartFile> images);
		public CommonResponse deletePostById(Post post);
		public CommonResponse selectAllPostByOpenId(Post post);
		public CommonResponse selectPostListPaging(PostQueryDTO postQueryDTO);
		
}




