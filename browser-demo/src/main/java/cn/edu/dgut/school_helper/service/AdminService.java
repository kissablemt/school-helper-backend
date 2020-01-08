package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Admin;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface AdminService{
	
		public CommonResponse selectAdminById(Admin admin);
		public CommonResponse addAdmin(Admin admin);
		public CommonResponse updateAdminById(Admin admin);
		public CommonResponse deleteAdminById(Admin admin);
		//public CommonResponse selectAllAdmin();
}




