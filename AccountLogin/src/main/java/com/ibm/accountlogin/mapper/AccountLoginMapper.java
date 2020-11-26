package com.ibm.accountlogin.mapper;

import com.ibm.accountlogin.dto.UserDto;
import com.ibm.accountlogin.entity.UserEntity;

public class AccountLoginMapper {

	public UserEntity convertToEntity(UserDto dto) {

		UserEntity entity = new UserEntity();
		entity.setUserid(dto.getUserid());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		return entity;
	}

	public UserDto convertToDto(UserEntity user) {

		UserDto dto = new UserDto();
		dto.setUserid(dto.getUserid());
		dto.setUsername(dto.getUsername());
		return dto;
	}
}
