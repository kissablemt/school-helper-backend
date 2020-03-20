package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.UserLogMapper;
import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.service.UserLogService;


@Service
public class UserLogServiceImpl implements UserLogService {
	
	@Autowired
	private UserLogMapper userLogMapper;
	

	@Override
	public JsonResult addUserLog(UserLog userLog) {
		int row = userLogMapper.insertSelective(userLog);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("插入失败");
	}

	@Override
	public JsonResult updateUserLog(UserLog userLog) {
		int row = userLogMapper.updateByPrimaryKeySelective(userLog);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("更新失败");
	}

	@Override
	public JsonResult deleteUserLogById(UserLog userLog) {
		int row = userLogMapper.deleteByPrimaryKey(userLog);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("删除失败");
	}

	@Override
	public JsonResult selectUserLogByOpenId(UserLog userLog) {
		return null;
	}

}
