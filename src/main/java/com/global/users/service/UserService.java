package com.global.users.service;

import java.util.UUID;

import com.global.users.DTO.UserLoginDTO;
import com.global.users.entity.User;

public interface UserService {
	
	public User save(User user);
	
	public UserLoginDTO getUserById(UUID id);

}
