package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface UserLogService {
	public JsonResult addUserLog(UserLog userLog);
}
