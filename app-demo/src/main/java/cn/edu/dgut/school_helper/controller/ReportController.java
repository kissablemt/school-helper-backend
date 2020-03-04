package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public CommonResponse addReport(@RequestBody Report report,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.addReport(report.setReporterId(openId));
	}

	@PutMapping
	public CommonResponse updateReport(@RequestBody Report report,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.updateReport(report.setReporterId(openId));
	}
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllReport(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.selectReportByOpenId(new Report().setReporterId(openId));
	}
}
