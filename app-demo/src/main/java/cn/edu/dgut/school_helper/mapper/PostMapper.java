package cn.edu.dgut.school_helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostOutputDTO;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface PostMapper extends tk.mybatis.mapper.common.Mapper<Post> {

	List<PostOutputDTO> selectAllPostByOpenId(String openId);

	List<PostOutputDTO> selectPostListPaging(@Param("postType") Integer postType, @Param("goodsType") Integer goodsType);
}
