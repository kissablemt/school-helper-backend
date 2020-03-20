package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostInputDTO;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.service.MiniAppService;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    MiniAppService miniAppService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @PostMapping
    public JsonResult addPost(@RequestBody PostInputDTO postInputDTO,
                              @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        String[] imageStrs = postInputDTO.getImages().split(",");
		//敏感图片验证不通过
		if (!miniAppService.validateSensitiveImage(imageStrs)) {
            return JsonResult.errorSensitiveContent("含有敏感图片");
		}
        Post post = new Post().setOpenId(openId)
                .setHeadline(postInputDTO.getHeadline())
                .setContent(postInputDTO.getContent())
                .setPostType(postInputDTO.getPostType())
                .setGoodsType(postInputDTO.getGoodsType())
                .setMoney(postInputDTO.getMoney())
                .setDate(new Date());
        return postService.addPost(post.setOpenId(openId), imageStrs);
    }

    @PutMapping
    public JsonResult updatePost(@RequestBody PostInputDTO postInputDTO,
                                 @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        String[] imageStrs = postInputDTO.getImages().split(",");
        //敏感图片验证不通过
        if (!miniAppService.validateSensitiveImage(imageStrs)) {
            return JsonResult.errorSensitiveContent("含有敏感图片");
        }
        Post post = new Post().setOpenId(openId)
                .setHeadline(postInputDTO.getHeadline())
                .setContent(postInputDTO.getContent())
                .setPostId(postInputDTO.getPostId())
                .setPostType(postInputDTO.getPostType())
                .setGoodsType(postInputDTO.getGoodsType())
                .setMoney(postInputDTO.getMoney());
        return postService.updatePost(post.setOpenId(openId), imageStrs);
    }

    @DeleteMapping("/{postId}")
    public JsonResult deletePostById(@PathVariable(name = "postId") Integer postId,
                                     @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return postService.deletePostById(new Post().setPostId(postId).setOpenId(openId));
    }

    @GetMapping("/selectAll")
    public JsonResult selectAllPostByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return postService.selectAllPostByOpenId(new Post().setOpenId(openId));
    }

    @GetMapping("/selectList")
    public JsonResult selectAllPostList(@ModelAttribute PostQueryDTO postQueryDTO) {
        log.info(ReflectionToStringBuilder.toString(postQueryDTO, ToStringStyle.MULTI_LINE_STYLE));
        return postService.selectPostListPaging(postQueryDTO);
    }

    @GetMapping("/selectSecondHandList")
    public JsonResult selectSecondHandPostListPaging(@ModelAttribute PostQueryDTO postQueryDTO) {
        log.info(ReflectionToStringBuilder.toString(postQueryDTO, ToStringStyle.MULTI_LINE_STYLE));
        return postService.selectSecondHandPostListPaging(postQueryDTO);
    }

}
