package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.service.SchoolService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public CommonResponse selectAllSchool() {
		return CommonResponse.isOk(schoolMapper.selectAll());
	}
}
