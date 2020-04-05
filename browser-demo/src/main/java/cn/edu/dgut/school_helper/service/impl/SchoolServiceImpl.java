package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.pojo.School;
import cn.edu.dgut.school_helper.service.SchoolService;
import springfox.documentation.spring.web.json.Json;


@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolMapper schoolMapper;
	

	@Override
	public JsonResult addSchool(School school) {
		int row = schoolMapper.insertSelective(school);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("插入失败");
	}

	@Override
	public JsonResult updateSchool(School school) {
		int row = schoolMapper.updateByPrimaryKeySelective(school);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("更新失败");
	}

	@Override
	public JsonResult deleteSchoolById(School school) {
		int row = schoolMapper.deleteByPrimaryKey(school);
		if(row == 1) {
			return JsonResult.ok(row);
		}
		return JsonResult.errorMsg("删除失败");
	}

	@Override
	public JsonResult selectSchoolByOpenId(School school) {
		return JsonResult.ok(schoolMapper.selectAll());
	}

	@Override
	public JsonResult selectAllSchool() {
		return JsonResult.ok(schoolMapper.selectAll());
	}
}
