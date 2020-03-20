package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Admin;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface AdminService{
	
		public JsonResult selectAdminById(Admin admin);
		public JsonResult addAdmin(Admin admin);
		public JsonResult updateAdminById(Admin admin);
		public JsonResult deleteAdminById(Admin admin);
		//public JsonResult selectAllAdmin();
}




