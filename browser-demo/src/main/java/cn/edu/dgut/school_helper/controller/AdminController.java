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

import cn.edu.dgut.school_helper.pojo.Admin;
import cn.edu.dgut.school_helper.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public JsonResult addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}

	@PutMapping
	public JsonResult updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdminById(admin);
	}
	
	@DeleteMapping("/{id}")
	public JsonResult deleteAdminById(@PathVariable(name = "id") Integer adminId) {
		return adminService.deleteAdminById(new Admin().setAdminId(adminId));
	}
	
	
	@GetMapping("/selectAll")
	public JsonResult selectAllAdmin() {
		return adminService.selectAdminById(null);
	}
}
