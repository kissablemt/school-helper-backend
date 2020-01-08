package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.CollectionMapper;
import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.service.CollectionService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class CollectionServiceImpl implements CollectionService {
	
	@Autowired
	private CollectionMapper collectionMapper;
	

	@Override
	public CommonResponse addCollection(Collection collection) {
		int row = collectionMapper.insertSelective(collection);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateCollection(Collection collection) {
		int row = collectionMapper.updateByPrimaryKeySelective(collection);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteCollectionById(Collection collection) {
		int row = collectionMapper.deleteByPrimaryKey(collection);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectCollectionByOpenId(Collection collection) {
		return CommonResponse.isOk(collectionMapper.selectAll());
	}
}
