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

import cn.edu.dgut.school_helper.pojo.Admin;
import cn.edu.dgut.school_helper.service.AdminService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public CommonResponse addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}

	@PutMapping
	public CommonResponse updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdminById(admin);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteAdminById(@PathVariable(name = "id") Integer adminId) {
		return adminService.deleteAdminById(new Admin().setAdminId(adminId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllAdmin() {
		return adminService.selectAdminById(null);
	}
}
