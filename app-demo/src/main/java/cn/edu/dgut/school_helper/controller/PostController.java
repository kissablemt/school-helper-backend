package cn.edu.dgut.school_helper.controller;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	private static final Logger log = LoggerFactory.getLogger(PostController.class);

	
	@PostMapping
	public CommonResponse addPost(@RequestBody Post post,List<MultipartFile> images) {
		return postService.addPost(post,images);
	}

	@PutMapping
	public CommonResponse updatePost(@RequestBody Post post,List<MultipartFile> images) {
		return postService.updatePost(post,images);
	}
	
	@DeleteMapping("/{postId}")
	public CommonResponse deletePostById(@PathVariable(name = "id") Integer postId) {
		return postService.deletePostById(new Post().setPostId(postId));
	}
	
	
	@GetMapping("/selectAll/{openId}")
	public CommonResponse selectAllPostByOpenId(@PathVariable(name="openId") String openId) {
		return postService.selectAllPostByOpenId(new Post().setOpenId(openId));
	}
	
	@GetMapping("/selectList")
	public CommonResponse selectAllPost(@ModelAttribute PostQueryDTO postQueryDTO) {
		log.info(ReflectionToStringBuilder.toString(postQueryDTO, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(postQueryDTO.toString());
		return postService.selectPostListPaging(postQueryDTO);
	}
}
