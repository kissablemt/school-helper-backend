package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface UserService{
	/**
	 * 把未在数据库的用户存入数据库中
	 */
	JsonResult addUser(User user);
	
	/**
	 * 获取用户信息
	 */
	JsonResult getUserInfo(User user);
	
	/**
	 * 查询是否有该用户
	 */
	JsonResult checkUserExistByOpenId(User user);

	/**
	 * 根据传入的base64字符串,更换头像
	 */
	JsonResult updateHeadPortrait(String openId, String headPortrait);

	/**
	 * 更新用户信息，除开头像地址
	 */
	JsonResult updateUser(User user);
}




