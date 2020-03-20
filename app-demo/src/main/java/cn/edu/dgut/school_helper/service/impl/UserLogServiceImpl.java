package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.mapper.UserLogMapper;
import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.service.UserLogService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}
