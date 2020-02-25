package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.User;

public interface UserService{
	/**
	 * 把未在数据库的用户存入数据库中
	 */
	Boolean registUser(User user);
	/**
	 * 查询是否有该用户
	 */
	Boolean checkUserExistByOpenId(User user);
}




