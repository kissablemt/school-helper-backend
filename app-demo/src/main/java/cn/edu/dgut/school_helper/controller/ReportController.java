package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public CommonResponse addReport(@RequestBody Report report) {
		return reportService.addReport(report);
	}

	@PutMapping
	public CommonResponse updateReport(@RequestBody Report report) {
		return reportService.updateReport(report);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteReportById(@PathVariable(name = "id") Integer reportId) {
		return reportService.deleteReportById(new Report().setReportId(reportId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllReport() {
		return reportService.selectReportByOpenId(null);
	}
}
