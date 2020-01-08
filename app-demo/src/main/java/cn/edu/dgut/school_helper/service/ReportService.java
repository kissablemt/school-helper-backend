package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface ReportService{
	
		public CommonResponse selectReportByOpenId(Report report);
		public CommonResponse addReport(Report report);
		public CommonResponse updateReport(Report report);
		public CommonResponse deleteReportById(Report report);
		//public CommonResponse selectAllReport();
}




