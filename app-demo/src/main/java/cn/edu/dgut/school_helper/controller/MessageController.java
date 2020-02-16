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

import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.service.MessageService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping
	public CommonResponse addMessage(@RequestBody Message message) {
		return messageService.addMessage(message);
	}
	
	@PutMapping("/read/{id}")
	public CommonResponse readMessage(@PathVariable Integer messageId) {
		return messageService.readMessage(new Message().setMessageId(messageId));
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteMessageById(@PathVariable(name = "id") Integer messageId) {
		return messageService.deleteMessageById(new Message().setMessageId(messageId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllMessage() {
		return messageService.selectMessageByOpenId(new Message().setOpenId("ss"));
	}
}
