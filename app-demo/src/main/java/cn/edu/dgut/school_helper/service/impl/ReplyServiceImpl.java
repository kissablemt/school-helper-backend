package cn.edu.dgut.school_helper.service.impl;


import cn.edu.dgut.school_helper.exception.ServiceRuntimeException;
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
import cn.edu.dgut.school_helper.util.IntegerCompareUtils;
import cn.edu.dgut.school_helper.util.JsonResult;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
	public JsonResult addReply(Reply reply) {
		// 检查有没有该用户
		User fromUser = userMapper.selectByPrimaryKey(reply.getFromOpenId());
		User toUser = userMapper.selectByPrimaryKey(reply.getToOpenId());
		if (fromUser == null || toUser == null) {
			return JsonResult.errorMsg("没有该用户");
		}
		// 检查是否有该帖子
		Post post = postMapper.selectByPrimaryKey(reply.getPostId());
		if (post == null) {
			return JsonResult.errorMsg("没有该帖子");
		}
		// 检查是否有该父评论,且父评论为根评论
		if (reply.getParentId() != null) {
			Reply reply2 = replyMapper.selectByPrimaryKey(reply.getParentId());
			if (reply2 == null) {
				return JsonResult.errorMsg("没有该父评论");
			}
			if(!IntegerCompareUtils.equals(reply2.getParentId(), -1)){
				return  JsonResult.errorMsg("父评论非根评论");
			}
		}else {
			//根评论
			reply.setParentId(-1);
		}
		// 插入评论
		reply.setDate(new Date());
		reply.setStatus(ReplyConstant.UNDELETE);
		int row = replyMapper.insertSelective(reply);
		if (row != 1) {
			return JsonResult.errorMsg("插入回复失败");
		}
		// 发送消息，有人回复他了
		Message message = new Message()
				.setOpenId(reply.getToOpenId())
				.setContent(reply.getContent())
				.setReplyId(reply.getReplyId())
				.setDate(new Date())
				.setStatus(MessageConstant.UNREAD);
		if(messageMapper.insertSelective(message) != 1) {
			throw new ServiceRuntimeException("发送消息失败");
		}
		return JsonResult.ok(row);
	}

	@Override
	@Transactional
	public JsonResult deleteReplyById(Reply reply) {
		// 非评论发起者，不允许删除
		Reply reply2 = replyMapper.selectByPrimaryKey(reply.getReplyId());
		if(reply2 == null) {
			return JsonResult.errorMsg("没有该回复");
		}
		if (!StringUtils.equals(reply2.getFromOpenId(), reply.getFromOpenId())) {
			return JsonResult.errorMsg("不能删除非自己发起的评论");
		}
		// 可删除评论,同时删除子评论
		int row = replyMapper.updateByPrimaryKeySelective(reply.setStatus(ReplyConstant.DELETED));
		List<Reply> childReplies =  replyMapper.select(new Reply().setParentId(reply.getReplyId()));
		for (Reply child: childReplies) {
			replyMapper.updateByPrimaryKeySelective(child.setStatus(ReplyConstant.DELETED));
		}
		if (row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("删除失败");
	}

	@Override
	public JsonResult selectReplyByPostId(Integer postId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return JsonResult.ok(replyMapper.selectAllReplyByPostId(postId));
	}
}
