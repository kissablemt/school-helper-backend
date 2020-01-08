package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.service.PostService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostMapper postMapper;
	

	@Override
	public CommonResponse addPost(Post post) {
		int row = postMapper.insertSelective(post);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updatePost(Post post) {
		int row = postMapper.updateByPrimaryKeySelective(post);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deletePostById(Post post) {
		int row = postMapper.deleteByPrimaryKey(post);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectPostByOpenId(Post post) {
		return CommonResponse.isOk(postMapper.selectAll());
	}

	@Override
	public CommonResponse selectAllPost() {
		return null;
	}
}
