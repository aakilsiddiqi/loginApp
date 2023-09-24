package com.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.DTO.LoginDTO;
import com.loanapp.DTO.UserDTO;
import com.loanapp.response.LoginMessage;
import com.loanapp.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	// http://localhost:8085/user/add
	@PostMapping(path = "/add")
	public String saveUser(@RequestBody UserDTO userDTO) {
		String id = userService.addUser(userDTO);
		System.out.println("test");
		return id;
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
		LoginMessage response  = userService.loginUser(loginDTO);
		return ResponseEntity.ok(response);
	}
}
