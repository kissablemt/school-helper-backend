package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.constant.MessageConstant;
import cn.edu.dgut.school_helper.mapper.MessageMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.MessageService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public JsonResult addMessage(Message message) {
		//检查是否存在该用户
		User user = userMapper.selectByPrimaryKey(message.getOpenId());
		if(user == null) {
			return JsonResult.errorMsg("没有该用户");
		}
		message.setStatus(MessageConstant.UNREAD);
		message.setDate(new Date());
		int row = messageMapper.insertSelective(message);
		if (row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("插入失败");
	}

	@Override
	public JsonResult readMessage(Message message) {
		Message message2 = messageMapper.selectByPrimaryKey(message.getMessageId());
		if(message2 == null) {
			return JsonResult.errorMsg("没有该消息");
		}
		if (!StringUtils.equals(message.getOpenId(), message2.getOpenId())) {
			return JsonResult.errorMsg("不是本人的消息，不可已读");
		}
		
		int row = messageMapper.updateByPrimaryKeySelective(message.setStatus(MessageConstant.READED));
		if (row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("更新失败");
	}

	@Override
	public JsonResult deleteMessageById(Message message) {
		Message message2 = messageMapper.selectByPrimaryKey(message.getMessageId());
		if(message2 == null) {
			return JsonResult.errorMsg("没有该消息");
		}
		if (!StringUtils.equals(message.getOpenId(), message2.getOpenId())) {
			return JsonResult.errorMsg("不是本人的消息，不可删除");
		}
		int row = messageMapper.updateByPrimaryKeySelective(message.setStatus(MessageConstant.DELETED));
		if (row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("删除失败");
	}

	@Override
	public JsonResult selectMessageByOpenId(Message message) {
		return JsonResult.ok(messageMapper.selectAllMessageByOpenId(message.getOpenId()));
	}
}
