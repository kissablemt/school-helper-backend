package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface ReplyService{
	
		public CommonResponse selectReplyByPostId(Integer postId, Integer pageNum, Integer pageSize);
		public CommonResponse addReply(Reply reply);
		public CommonResponse deleteReplyById(Reply reply);
		//public CommonResponse selectAllReply();
}




