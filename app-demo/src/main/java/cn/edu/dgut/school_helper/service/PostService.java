package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface PostService{
	
		public CommonResponse selectPostByOpenId(Post post);
		public CommonResponse addPost(Post post);
		public CommonResponse updatePost(Post post);
		public CommonResponse deletePostById(Post post);
		public CommonResponse selectAllPost();
}




