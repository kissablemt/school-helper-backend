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

import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public CommonResponse addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PutMapping
	public CommonResponse updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteUserById(@PathVariable(name = "id") Integer userId) {
		return userService.deleteUserById(null);
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllUser() {
		return userService.selectUserByOpenId(new User().setOpenId("ss"));
	}
}
