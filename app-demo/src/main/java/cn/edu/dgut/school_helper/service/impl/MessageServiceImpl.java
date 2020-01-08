package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.MessageMapper;
import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.service.MessageService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	

	@Override
	public CommonResponse addMessage(Message message) {
		int row = messageMapper.insertSelective(message);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateMessage(Message message) {
		int row = messageMapper.updateByPrimaryKeySelective(message);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteMessageById(Message message) {
		int row = messageMapper.deleteByPrimaryKey(message);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectMessageByOpenId(Message message) {
		return CommonResponse.isOk(messageMapper.selectAll());
	}
}
