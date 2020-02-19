package cn.edu.dgut.school_helper.service.impl;

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
		// 不知道会不会有bug integer比较,想着static final 没得问题
		if (post == null || PostConstant.DELETED == post.getStatus()) {
			return CommonResponse.error("没有该帖子");
		}
		int row = collectionMapper.insertSelective(collection);
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse deleteCollectionById(Collection collection) {
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
