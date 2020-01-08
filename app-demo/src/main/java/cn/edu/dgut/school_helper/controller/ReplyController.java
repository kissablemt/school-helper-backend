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

import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping
	public CommonResponse addReply(@RequestBody Reply reply) {
		return replyService.addReply(reply);
	}

	@PutMapping
	public CommonResponse updateReply(@RequestBody Reply reply) {
		return replyService.updateReply(reply);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteReplyById(@PathVariable(name = "id") Integer replyId) {
		return replyService.deleteReplyById(new Reply().setReplyId(replyId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllReply() {
		return replyService.selectReplyByOpenId(null);
	}
}
