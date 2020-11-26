package com.ibm.accountlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.accountlogin.entity.UserEntity;

@Repository
public interface AccountLoginRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUsername(String username);

}
