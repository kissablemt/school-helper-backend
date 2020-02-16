package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface MessageService{
	
		public CommonResponse selectMessageByOpenId(Message message);
		public CommonResponse addMessage(Message message);
		public CommonResponse readMessage(Message message);
		public CommonResponse deleteMessageById(Message message);
		//public CommonResponse selectAllMessage();
}




