package cn.edu.dgut.school_helper.controller;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostInputDTO;
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
	public CommonResponse addPost(@RequestBody PostInputDTO postInputDTO,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		String[] imagesStr = postInputDTO.getImages().split(",");
		Post post = new Post().setOpenId(openId)
				.setHeadline(postInputDTO.getHeadline())
				.setContent(postInputDTO.getContent())
				.setPostType(postInputDTO.getPostType())
				.setGoodsType(postInputDTO.getGoodsType())
				.setMoney(postInputDTO.getMoney())
				.setDate(new Date());
		return postService.addPost(post.setOpenId(openId), imagesStr);
	}

	@PutMapping
	public CommonResponse updatePost(@RequestBody PostInputDTO postInputDTO,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		String[] imagesStr = postInputDTO.getImages().split(",");
		Post post = new Post().setOpenId(openId)
				.setHeadline(postInputDTO.getHeadline())
				.setContent(postInputDTO.getContent())
				.setPostId(postInputDTO.getPostId())
				.setPostType(postInputDTO.getPostType())
				.setGoodsType(postInputDTO.getGoodsType())
				.setMoney(postInputDTO.getMoney());
		return postService.updatePost(post.setOpenId(openId), imagesStr);
	}

	@DeleteMapping("/{postId}")
	public CommonResponse deletePostById(@PathVariable(name = "postId") Integer postId,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return postService.deletePostById(new Post().setPostId(postId).setOpenId(openId));
	}

	@GetMapping("/selectAll")
	public CommonResponse selectAllPostByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return postService.selectAllPostByOpenId(new Post().setOpenId(openId));
	}

	@GetMapping("/selectList")
	public CommonResponse selectAllPostList(@ModelAttribute PostQueryDTO postQueryDTO) {
		log.info(ReflectionToStringBuilder.toString(postQueryDTO, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(postQueryDTO.toString());
		return postService.selectPostListPaging(postQueryDTO);
	}
}
