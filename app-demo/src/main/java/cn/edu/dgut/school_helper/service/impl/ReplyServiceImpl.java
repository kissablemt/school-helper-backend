package cn.edu.dgut.school_helper.service.impl;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.dgut.school_helper.config.response.ServiceRuntimeExecption;
import cn.edu.dgut.school_helper.constant.MessageConstant;
import cn.edu.dgut.school_helper.constant.ReplyConstant;
import cn.edu.dgut.school_helper.mapper.MessageMapper;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.ReplyMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Message;
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

	@Autowired
	private MessageMapper messageMapper;

	@Override
	@Transactional
	public CommonResponse addReply(Reply reply) {
		// 检查有没有该用户
		User fromUser = userMapper.selectByPrimaryKey(reply.getFromOpenId());
		User toUser = userMapper.selectByPrimaryKey(reply.getToOpenId());
		if (fromUser == null || toUser == null) {
			return CommonResponse.error("没有该用户");
		}
		// 检查是否有该帖子
		Post post = postMapper.selectByPrimaryKey(reply.getPostId());
		if (post == null) {
			return CommonResponse.error("没有该帖子");
		}
		// 检查是否有该父评论
		if (reply.getParentId() != null) {
			Reply reply2 = replyMapper.selectByPrimaryKey(reply.getParentId());
			if (reply2 == null) {
				return CommonResponse.error("没有该父评论");
			}
		}
		// 发送消息，有人回复他了
		Message message = new Message()
				.setOpenId(reply.getToOpenId())
				.setContent(reply.getContent())
				.setDate(new Date())
				.setStatus(MessageConstant.UNREAD);
		if(messageMapper.insertSelective(message) != 1) {
			return CommonResponse.error("发送消息失败");
		}
		//插入评论
		int row = replyMapper.insertSelective(reply);
		if (row != 1) {
			throw new ServiceRuntimeExecption("插入回复失败");
		}
		return CommonResponse.isOk("插入成功");
	}

	@Override
	public CommonResponse deleteReplyById(Reply reply) {
		// 非评论发起者，不允许删除
		Reply reply2 = replyMapper.selectByPrimaryKey(reply.getReplyId());
		if (reply2.getFromOpenId() != reply.getFromOpenId()) {
			return CommonResponse.error("不能删除非自己发起的评论");
		}
		int row = replyMapper.updateByPrimaryKeySelective(reply.setStatus(ReplyConstant.DELETED));
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectReplyByPostId(Integer postId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return CommonResponse.isOk(replyMapper.selectAllReplyByPostId(postId));
	}
}
