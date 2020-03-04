package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.service.MessageService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PutMapping("/read/{messageId}")
	public CommonResponse readMessage(@PathVariable(name="messageId") Integer messageId,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.readMessage(new Message().setMessageId(messageId).setOpenId(openId));
	}
	
	@DeleteMapping("/{messageId}")
	public CommonResponse deleteMessageById(@PathVariable(name = "messageId") Integer messageId,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.deleteMessageById(new Message().setMessageId(messageId).setOpenId(openId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllMessageByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.selectMessageByOpenId(new Message().setOpenId(openId));
	}
}
