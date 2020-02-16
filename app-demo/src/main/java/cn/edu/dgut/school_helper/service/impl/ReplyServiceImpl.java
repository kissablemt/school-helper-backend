package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.dgut.school_helper.constanst.ReplyConstant;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.ReplyMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PostMapper postMapper;

	@Override
	public CommonResponse addReply(Reply reply) {
		//检查有没有该用户
		User fromUser = userMapper.selectByPrimaryKey(reply.getFromOpenId());
		User toUser = userMapper.selectByPrimaryKey(reply.getToOpenId());
		if(fromUser == null || toUser == null) {
			return CommonResponse.error("没有该用户");
		}
		//检查是否有该帖子
		Post post = postMapper.selectByPrimaryKey(reply.getPostId());
		if(post == null) {
			return CommonResponse.error("没有该帖子");
		}
		//检查是否有该父评论
		if(reply.getParentId() != null) {
			Reply reply2 =  replyMapper.selectByPrimaryKey(reply.getParentId());
			if(reply2 == null) {
				return CommonResponse.error("没有该父评论");
			} 
		}
		int row = replyMapper.insertSelective(reply);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse deleteReplyById(Reply reply) {
		//非评论发起者，不允许删除
		Reply reply2 =  replyMapper.selectByPrimaryKey(reply.getReplyId());
		if(reply2.getFromOpenId() != reply.getFromOpenId()) {
			return CommonResponse.error("不能删除非自己发起的评论");
		}
		int row = replyMapper.updateByPrimaryKeySelective(reply.setStatus(ReplyConstant.DELETED));
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectReplyByPostId(Reply reply) {
//		PageHelper.startPage(0, 1);
		return CommonResponse.isOk(replyMapper.selectAllReplyByPostId(reply.getPostId()));
	}
}
