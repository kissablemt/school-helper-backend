package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.ReportMapper;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportMapper reportMapper;
	

	@Override
	public CommonResponse addReport(Report report) {
		int row = reportMapper.insertSelective(report);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateReport(Report report) {
		int row = reportMapper.updateByPrimaryKeySelective(report);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteReportById(Report report) {
		int row = reportMapper.deleteByPrimaryKey(report);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectReportByOpenId(Report report) {
		return CommonResponse.isOk(reportMapper.selectAll());
	}
}
