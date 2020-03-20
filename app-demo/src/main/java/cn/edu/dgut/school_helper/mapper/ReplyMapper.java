package cn.edu.dgut.school_helper.mapper;

import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.pojo.dto.ReplyDTO;

import java.util.List;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface ReplyMapper extends tk.mybatis.mapper.common.Mapper<Reply> {

	List<ReplyDTO> selectAllReplyByPostId(Integer postId);
}
