package cn.edu.dgut.school_helper.mapper;

import java.util.List;

import cn.edu.dgut.school_helper.pojo.Collection;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface CollectionMapper extends tk.mybatis.mapper.common.Mapper<Collection> {
	List<Collection> selectAllCollectionByOpenId(String openId);
}




