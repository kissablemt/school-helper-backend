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

import cn.edu.dgut.school_helper.pojo.Audit;
import cn.edu.dgut.school_helper.service.AuditService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/audit")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@PostMapping
	public CommonResponse addAudit(@RequestBody Audit audit) {
		return auditService.addAudit(audit);
	}

	@PutMapping
	public CommonResponse updateAudit(@RequestBody Audit audit) {
		return auditService.updateAudit(audit);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteAuditById(@PathVariable(name = "id") Integer auditId) {
		return auditService.deleteAuditById(new Audit().setAuditId(auditId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllAudit() {
		return auditService.selectAuditByOpenId(null);
	}
}
