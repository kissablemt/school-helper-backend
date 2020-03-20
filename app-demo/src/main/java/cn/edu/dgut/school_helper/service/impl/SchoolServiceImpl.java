package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.service.SchoolService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public JsonResult selectAllSchool() {
		return JsonResult.ok(schoolMapper.selectAll());
	}
}
