package cn.edu.dgut.school_helper.mapper;

import cn.edu.dgut.school_helper.pojo.Image;

import java.util.List;


/**
 * @author 星星星
 *  image的信息非假删除
 */
public interface ImageMapper extends tk.mybatis.mapper.common.Mapper<Image> {
	List<Image> selectAllImageByPostId(Integer postId);
}




