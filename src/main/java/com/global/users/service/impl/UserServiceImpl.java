package com.global.users.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.users.DAO.UserDAO;
import com.global.users.DTO.UserLoginDTO;
import com.global.users.entity.User;
import com.global.users.exception.EmailExistsException;
import com.global.users.exception.UserNotFoundException;
import com.global.users.security.JWTToken;
import com.global.users.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private JWTToken jwtToken;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
	
		this.existsEmail(user);
		user.setCreated(LocalDateTime.now());
		user.setActive(true);
		user.setLastLogin(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User newUser = this.userDAO.save(user);
		newUser.setToken(this.generateTokenById(newUser.getId()));

		return newUser;
	}

	@Override
	public UserLoginDTO getUserById(UUID id) {
		Optional<User> userOpt = userDAO.findById(id);
		User user = null;
		this.isPresentUser(userOpt);
		user = userOpt.get();
		user.setLastLogin(LocalDateTime.now());
		
		return modelMapper.map(user, UserLoginDTO.class);
	}
	
	private String generateTokenById(UUID id) {
		String token = jwtToken.getJWTToken(id);
		userDAO.updateTokenById(token, id);
		return token;
	}

	private void existsEmail(User user) {
		if (userDAO.findUserByEmail(user.getEmail()).isPresent()) {
			throw new EmailExistsException();
		}
	}
	
	private void isPresentUser(Optional<User> user) {
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
	}
}
