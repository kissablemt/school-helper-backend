package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.UserLogMapper;
import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.service.UserLogService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class UserLogServiceImpl implements UserLogService {
	
	@Autowired
	private UserLogMapper userLogMapper;
	

	@Override
	public CommonResponse addUserLog(UserLog userLog) {
		int row = userLogMapper.insertSelective(userLog);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateUserLog(UserLog userLog) {
		int row = userLogMapper.updateByPrimaryKeySelective(userLog);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteUserLogById(UserLog userLog) {
		int row = userLogMapper.deleteByPrimaryKey(userLog);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectUserLogByOpenId(UserLog userLog) {
		return null;
	}

}
