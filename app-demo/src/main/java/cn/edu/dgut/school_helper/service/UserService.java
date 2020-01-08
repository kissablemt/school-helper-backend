package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface UserService{
	
		public CommonResponse selectUserByOpenId(User user);
		public CommonResponse addUser(User user);
		public CommonResponse updateUser(User user);
		public CommonResponse deleteUserById(User user);
		public CommonResponse selectAllUser();
}




