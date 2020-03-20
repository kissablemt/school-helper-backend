package cn.edu.dgut.school_helper.mapper;

import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.pojo.dto.MessageDTO;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<Message> {
	List<MessageDTO> selectAllMessageByOpenId(String openId);
}




