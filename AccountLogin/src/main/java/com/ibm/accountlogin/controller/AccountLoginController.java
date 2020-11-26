package com.ibm.accountlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.accountlogin.dto.UserDto;
import com.ibm.accountlogin.service.AccountLoginService;

@RequestMapping("login")
@RestController
public class AccountLoginController {
	@Autowired
	AccountLoginService accountLoginService;
	@GetMapping("/createToken/{username}")
	public String createToken(@PathVariable("username")String username) {
		String token=accountLoginService.createToken(username);
		if(!token.isEmpty()) {
			return token;
		}
		return "Bad Request";
		
	}
	
	@PostMapping("/saveUser")
	public String saveUserDetails(@RequestBody UserDto userDto) {
		return accountLoginService.saveUserDetails(userDto);
	}
}
