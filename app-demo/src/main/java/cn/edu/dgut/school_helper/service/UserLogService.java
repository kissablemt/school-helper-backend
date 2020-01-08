package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface UserLogService{
	
		public CommonResponse selectUserLogByOpenId(UserLog userLog);
		public CommonResponse addUserLog(UserLog userLog);
		public CommonResponse updateUserLog(UserLog userLog);
		public CommonResponse deleteUserLogById(UserLog userLog);
		//public CommonResponse selectAllUserLog();
}




