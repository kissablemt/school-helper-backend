package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.pojo.School;
import cn.edu.dgut.school_helper.service.SchoolService;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@PostMapping
	public JsonResult addSchool(@RequestBody School school) {
		return schoolService.addSchool(school);
	}

	@PutMapping
	public JsonResult updateSchool(@RequestBody School school) {
		return schoolService.updateSchool(school);
	}
	
	@DeleteMapping("/{id}")
	public JsonResult deleteSchoolById(@PathVariable(name = "id") Integer schoolId) {
		return schoolService.deleteSchoolById(new School().setSchoolId(schoolId));
	}
	
	
	@GetMapping("/selectAll")
	public JsonResult selectAllSchool() {
		return schoolService.selectSchoolByOpenId(null);
	}
}
