package cn.edu.dgut.school_helper.mapper;

import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostOutputDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface PostMapper extends tk.mybatis.mapper.common.Mapper<Post> {

    PostOutputDTO selectPostByPostId(Integer postId);

    List<PostOutputDTO> selectAllPostByOpenId(String openId);

    List<PostOutputDTO> selectPostListPaging(@Param("postType") Integer postType, @Param("goodsType") Integer goodsType, @Param("keyword") String keyword);

    List<PostOutputDTO> selectSecondHandPostListPaging(String keyword);
}
