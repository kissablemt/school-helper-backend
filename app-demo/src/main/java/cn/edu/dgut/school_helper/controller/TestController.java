package cn.edu.dgut.school_helper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.config.response.ResponseResult;
import cn.edu.dgut.school_helper.config.response.ServiceRuntimeExecption;


@ResponseResult
@RestController
public class TestController {
	
	@RequestMapping("/api/test")
	public String test() {
		throw new ServiceRuntimeExecption("错误");
	}
	
}
