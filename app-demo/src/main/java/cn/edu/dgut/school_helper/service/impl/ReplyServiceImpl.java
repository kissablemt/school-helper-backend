package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.ReplyMapper;
import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	

	@Override
	public CommonResponse addReply(Reply reply) {
		int row = replyMapper.insertSelective(reply);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateReply(Reply reply) {
		int row = replyMapper.updateByPrimaryKeySelective(reply);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteReplyById(Reply reply) {
		int row = replyMapper.deleteByPrimaryKey(reply);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectReplyByOpenId(Reply reply) {
		return CommonResponse.isOk(replyMapper.selectAll());
	}
}
