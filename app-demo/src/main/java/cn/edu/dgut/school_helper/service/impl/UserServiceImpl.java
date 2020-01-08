package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public CommonResponse addUser(User user) {
		int row = userMapper.insertSelective(user);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateUser(User user) {
		int row = userMapper.updateByPrimaryKeySelective(user);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteUserById(User user) {
		int row = userMapper.deleteByPrimaryKey(user);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectUserByOpenId(User user) {
		return CommonResponse.isOk(userMapper.selectAll());
	}

	@Override
	public CommonResponse selectAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
