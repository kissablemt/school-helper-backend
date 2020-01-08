package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.pojo.School;
import cn.edu.dgut.school_helper.service.SchoolService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolMapper schoolMapper;
	

	@Override
	public CommonResponse addSchool(School school) {
		int row = schoolMapper.insertSelective(school);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateSchool(School school) {
		int row = schoolMapper.updateByPrimaryKeySelective(school);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteSchoolById(School school) {
		int row = schoolMapper.deleteByPrimaryKey(school);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectSchoolByOpenId(School school) {
		return CommonResponse.isOk(schoolMapper.selectAll());
	}

	@Override
	public CommonResponse selectAllSchool() {
		// TODO Auto-generated method stub
		return null;
	}
}
