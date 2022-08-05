package com.global.users.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.global.users.DTO.UserLoginDTO;
import com.global.users.DTO.UserRequestDTO;
import com.global.users.DTO.UserResponseDTO;

import com.global.users.entity.User;
import com.global.users.exception.EmailExistsException;
import com.global.users.exception.UserNotFoundException;
import com.global.users.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService service;
	
	private ModelMapper modelMapper;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws EmailExistsException {
		return new ResponseEntity<UserResponseDTO>(this.createUser(userRequestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUserByToken(@RequestAttribute("id") UUID id) throws Exception {
		return new ResponseEntity<UserLoginDTO>(this.getUserById(id), HttpStatus.OK);
	}
	
	private UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		User newUser = this.service.save(this.modelMapper.map(userRequestDTO, User.class));
		return this.modelMapper.map(newUser, UserResponseDTO.class);
	}
	
	private UserLoginDTO getUserById(UUID id) throws UserNotFoundException {
		return this.service.getUserById(id);
	}
	
}
