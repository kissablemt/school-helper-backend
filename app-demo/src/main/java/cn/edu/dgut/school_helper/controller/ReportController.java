package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public JsonResult addReport(@RequestBody Report report,
								@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.addReport(report.setReporterId(openId));
	}

	@PutMapping
	public JsonResult updateReport(@RequestBody Report report,
			@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.updateReport(report.setReporterId(openId));
	}
	
	@GetMapping("/selectAll")
	public JsonResult selectAllReport(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return reportService.selectReportByOpenId(new Report().setReporterId(openId));
	}
}
