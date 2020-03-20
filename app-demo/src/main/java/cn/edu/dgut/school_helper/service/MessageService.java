package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface MessageService{
	
		public JsonResult selectMessageByOpenId(Message message);
		public JsonResult addMessage(Message message);
		public JsonResult readMessage(Message message);
		public JsonResult deleteMessageById(Message message);
		//public JsonResult selectAllMessage();
}




