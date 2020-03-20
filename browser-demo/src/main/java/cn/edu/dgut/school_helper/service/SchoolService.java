package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.School;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface SchoolService{
	
		public JsonResult selectSchoolByOpenId(School school);
		public JsonResult addSchool(School school);
		public JsonResult updateSchool(School school);
		public JsonResult deleteSchoolById(School school);
		public JsonResult selectAllSchool();
}




