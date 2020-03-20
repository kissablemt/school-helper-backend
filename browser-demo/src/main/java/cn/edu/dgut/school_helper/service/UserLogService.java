package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface UserLogService{
	
		public JsonResult selectUserLogByOpenId(UserLog userLog);
		public JsonResult addUserLog(UserLog userLog);
		public JsonResult updateUserLog(UserLog userLog);
		public JsonResult deleteUserLogById(UserLog userLog);
		//public JsonResult selectAllUserLog();
}




