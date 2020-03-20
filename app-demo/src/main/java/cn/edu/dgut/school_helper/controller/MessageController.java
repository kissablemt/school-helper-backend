package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.service.MessageService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PutMapping("/read/{messageId}")
	public JsonResult readMessage(@PathVariable(name="messageId") Integer messageId, @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.readMessage(new Message().setMessageId(messageId).setOpenId(openId));
	}
	
	@DeleteMapping("/{messageId}")
	public JsonResult deleteMessageById(@PathVariable(name = "messageId") Integer messageId,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.deleteMessageById(new Message().setMessageId(messageId).setOpenId(openId));
	}
	
	
	@GetMapping("/selectAll")
	public JsonResult selectAllMessageByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return messageService.selectMessageByOpenId(new Message().setOpenId(openId));
	}
}
