package com.ibm.accountlogin.service;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.accountlogin.dto.UserDto;
import com.ibm.accountlogin.entity.UserEntity;
import com.ibm.accountlogin.mapper.AccountLoginMapper;
import com.ibm.accountlogin.repository.AccountLoginRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccountLoginService {
	
	@Autowired
	AccountLoginRepository accountLoginRepository;

	public String createToken(String username) {
		UserEntity userEntity=accountLoginRepository.findByUsername(username);
		String token="";
		if(userEntity != null && !userEntity.getUsername().isEmpty()) {
		String signKey = Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes());
		Calendar now = Calendar.getInstance();
		Date issueTime = now.getTime();
		now.add(Calendar.MINUTE, 30);
		Date expiryTime = now.getTime();
		token = Jwts.builder()
				.setSubject(userEntity.getUsername())
				.setIssuedAt(issueTime)
				.claim("username", userEntity.getUsername())
				.setIssuer("JWT_KEY").setExpiration(expiryTime)
				.signWith(SignatureAlgorithm.HS256, signKey)
				.compact();		
		}
		return token;

	}
	
	
	
	public String saveUserDetails(UserDto userDto) {
		AccountLoginMapper mapper =new AccountLoginMapper();
		String signKey = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());
		userDto.setPassword(signKey);
		UserEntity userEntity=mapper.convertToEntity(userDto);
		accountLoginRepository.save(userEntity);
		return "Saved Successfully";
		
	}

}