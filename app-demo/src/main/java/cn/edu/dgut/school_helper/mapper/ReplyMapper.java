package cn.edu.dgut.school_helper.mapper;

import java.util.List;

import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.pojo.dto.ReplyDTO;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface ReplyMapper extends tk.mybatis.mapper.common.Mapper<Reply> {

	List<ReplyDTO> selectAllReplyByPostId(Integer postId);
}
