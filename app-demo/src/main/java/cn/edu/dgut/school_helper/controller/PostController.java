package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	public CommonResponse addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}

	@PutMapping
	public CommonResponse updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deletePostById(@PathVariable(name = "id") Integer postId) {
		return postService.deletePostById(new Post().setPostId(postId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllPost() {
		return postService.selectPostByOpenId(new Post().setOpenId("ss"));
	}
}
