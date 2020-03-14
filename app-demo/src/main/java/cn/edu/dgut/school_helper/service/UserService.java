package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface UserService{
	/**
	 * 把未在数据库的用户存入数据库中
	 */
	Boolean addUser(User user);
	
	/**
	 * 获取用户信息
	 */
	User getUserInfo(User user);
	
	/**
	 * 查询是否有该用户
	 */
	Boolean checkUserExistByOpenId(User user);

	/**
	 * 根据传入的base64字符串,更换头像
	 */
	CommonResponse updateHeadPortrait(String openId, String headPortrait);

	/**
	 * 更新用户信息，除开头像地址
	 */
	CommonResponse updateUser(User user);
}




