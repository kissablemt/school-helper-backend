package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.SchoolService;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SchoolMapper schoolMapper;

	@Override
	public String loginUser(User user) {
		User user2 = userMapper.selectByPrimaryKey(user);
		if(user2 == null) {
			return "error,system haven't this user";
		}
		//jwt构造
		String jwt = "xxx";
		return jwt;
	}

	@Override
	public Boolean registUser(User user) {
		//查看是否有该学校
		if(schoolMapper.selectByPrimaryKey(user.getSchoolId()) == null){
			return false;
		}
		//插入数据
		int row = userMapper.insertSelective(user);
		if(row != 1) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean checkUserExistByOpenId(User user) {
		User user2 = userMapper.selectByPrimaryKey(user.getOpenId());
		if(user2 == null) {
			return false;
		}
		return true;
	}

	
}
