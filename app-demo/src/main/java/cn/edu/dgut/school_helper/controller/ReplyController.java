package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@PostMapping
	public CommonResponse addReply(@RequestBody Reply reply,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return replyService.addReply(reply.setFromOpenId(openId));
	}

	@DeleteMapping("/{id}")
	public CommonResponse deleteReplyById(@PathVariable(name = "id") Integer replyId,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return replyService.deleteReplyById(new Reply().setReplyId(replyId).setFromOpenId(openId));
	}

	@GetMapping("/selectAll/{postId}")
	public CommonResponse selectReplyList(@PathVariable("postId") Integer postId, @RequestParam Integer pageNum,
			@RequestParam Integer pageSize) {
		return replyService.selectReplyByPostId(postId, pageNum, pageSize);
	}
}
