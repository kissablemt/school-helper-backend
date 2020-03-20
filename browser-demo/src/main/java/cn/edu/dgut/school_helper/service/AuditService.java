package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Audit;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface AuditService{
	
		public JsonResult selectAuditByOpenId(Audit audit);
		public JsonResult addAudit(Audit audit);
		public JsonResult updateAudit(Audit audit);
		public JsonResult deleteAuditById(Audit audit);
		//public JsonResult selectAllAudit();
}




