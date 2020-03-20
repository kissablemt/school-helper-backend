package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.service.SchoolService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/selectAll")
	public JsonResult selectAllSchool() {
		return schoolService.selectAllSchool();
	}
}
