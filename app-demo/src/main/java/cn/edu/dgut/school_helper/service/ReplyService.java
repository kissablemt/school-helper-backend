package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface ReplyService{
	
		public JsonResult selectReplyByPostId(Integer postId, Integer pageNum, Integer pageSize);
		public JsonResult addReply(Reply reply);
		public JsonResult deleteReplyById(Reply reply);
		//public JsonResult selectAllReply();
}




