package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.School;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface SchoolService{
	
		public CommonResponse selectSchoolByOpenId(School school);
		public CommonResponse addSchool(School school);
		public CommonResponse updateSchool(School school);
		public CommonResponse deleteSchoolById(School school);
		public CommonResponse selectAllSchool();
}




