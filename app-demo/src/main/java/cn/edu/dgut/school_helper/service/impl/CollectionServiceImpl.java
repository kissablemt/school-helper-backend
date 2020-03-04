package cn.edu.dgut.school_helper.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.constant.CollectionConstant;
import cn.edu.dgut.school_helper.constant.PostConstant;
import cn.edu.dgut.school_helper.mapper.CollectionMapper;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.CollectionService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionMapper collectionMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PostMapper postMapper;

	@Override
	public CommonResponse addCollection(Collection collection) {
		// 检查是否存在该用户
		User user = userMapper.selectByPrimaryKey(collection.getOpenId());
		if (user == null) {
			return CommonResponse.error("没有该用户");
		}
		// 检查是否存在该帖子
		Post post = postMapper.selectByPrimaryKey(collection.getPostId());
		if (post == null || PostConstant.DELETED == post.getStatus()) {
			return CommonResponse.error("没有该帖子");
		}
		collection.setStatus(CollectionConstant.UNDELETE);
		int row = collectionMapper.insertSelective(collection);
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse deleteCollectionById(Collection collection) {
		Collection collection2 = collectionMapper.selectByPrimaryKey(collection.getCollectionId());
		if (!StringUtils.equals(collection.getOpenId(), collection2.getOpenId())) {
			return CommonResponse.error("不是本人的收藏，不可删除");
		}
		
		int row = collectionMapper.updateByPrimaryKeySelective(collection.setStatus(CollectionConstant.DELETED));
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectCollectionByOpenId(Collection collection) {
		return CommonResponse.isOk(collectionMapper.selectAllCollectionByOpenId(collection.getOpenId()));
	}
}
