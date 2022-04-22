package com.globlal.users.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.global.users.DAO.UserDAO;
import com.global.users.DTO.UserLoginDTO;
import com.global.users.model.User;
import com.global.users.service.UserService;

public class UserServiceImplTest {
	
	@Autowired
	UserService userService;
	@Autowired
	UserDAO userDAO;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Test
	public void passwordEncoder() {
		
		User user = new User();
		String passTXT= "hola";
		String passEncode = passwordEncoder.encode(user.getPassword());
		assertEquals(passwordEncoder.encode(passTXT), passEncode);
		
	}
	
	@Test
	public void existUser() {
		
		Optional<User> user = userDAO.findById(null);
		assertTrue(user.isPresent());
		assertFalse(!user.isPresent());
		
	}
	
	@Test
	public void getUserById() {
		
		UserLoginDTO mainUser = new UserLoginDTO();
		mainUser.setActive(true);
		mainUser.setCreated(null);
		mainUser.setEmail(null);
		mainUser.setId(null);;
		mainUser.setLastLogin(null);
		mainUser.setName(null);
		mainUser.setPassword(null);
		mainUser.setPhones(null);
		mainUser.setToken(null);
		
		UserLoginDTO user = this.userService.getUserById(null);
		
		assertEquals(user, mainUser);
		
	}
	
	
	

}
