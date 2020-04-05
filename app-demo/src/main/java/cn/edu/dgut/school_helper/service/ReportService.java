package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface ReportService{

		@Deprecated
		public JsonResult selectReportByOpenId(Report report);
		@Deprecated
		public JsonResult addReport(Report report);
		@Deprecated
		public JsonResult updateReport(Report report);
		public JsonResult addReportAutoDealPost(Report report);
//		public JsonResult deleteReportById(Report report);
		//public JsonResult selectAllReport();
}




