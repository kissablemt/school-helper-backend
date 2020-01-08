package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Audit;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface AuditService{
	
		public CommonResponse selectAuditByOpenId(Audit audit);
		public CommonResponse addAudit(Audit audit);
		public CommonResponse updateAudit(Audit audit);
		public CommonResponse deleteAuditById(Audit audit);
		//public CommonResponse selectAllAudit();
}




