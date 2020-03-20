package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface ReportService{
	
		public JsonResult selectReportByOpenId(Report report);
		public JsonResult addReport(Report report);
		public JsonResult updateReport(Report report);
		public JsonResult deleteReportById(Report report);
		//public JsonResult selectAllReport();
}




