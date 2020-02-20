package cn.edu.dgut.school_helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostDTO;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface PostMapper extends tk.mybatis.mapper.common.Mapper<Post> {

	List<PostDTO> selectAllPostByOpenId(String openId);

	List<PostDTO> selectPostListPaging(@Param("postType") Integer postType, @Param("goodsType") Integer goodsType);
}
