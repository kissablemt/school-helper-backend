package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface UserService{
	/**
	 *  验证用户信息，成功返回jwt字符串
	 */
	String loginUser(User user);
	/**
	 * 把未在数据库的用户存入数据库中
	 */
	Boolean registUser(User user);
	/**
	 * 查询是否有该用户
	 */
	Boolean checkUserExistByOpenId(User user);
}




