package com.global.users.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.global.users.DAO.UserDAO;
import com.global.users.DTO.UserLoginDTO;
import com.global.users.entity.Phone;
import com.global.users.entity.User;
import com.global.users.security.JWTToken;
import com.global.users.service.UserService;
import com.global.users.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	UserDAO userDAO;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	PasswordEncoder passwordEncoder;
	@Mock
	private JWTToken jwtToken;
	
	public final static UUID USER_ID = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");
	
	
	@Test //cuando Ose obtiene el User por Id, retorna UserLoginDTO
	void whenGetUserById() {
		// given
		final User user = new User();
		user.setId(USER_ID);
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		userLoginDTO.setId(USER_ID);

		given(userDAO.findById(USER_ID)).willReturn(Optional.of(user));
		given(modelMapper.map(user, UserLoginDTO.class)).willReturn(userLoginDTO);

		// when
		UserLoginDTO result = userService.getUserById(USER_ID);

		// then
		assertTrue(result != null);
		assertThat(result.getId()).isEqualTo(USER_ID);
	}
	
	@Test //cuando se crea un User exitoso
	void whenCreateAUser() {
		// given
		final String name = "jose";
		final String email = "pruebaMock@prueba.com";
		final List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setNumber(1234L);
		phone.setCityCode(123);
		phone.setCountryCode("ARG");
		phones.add(phone);
		final LocalDateTime created = LocalDateTime.now();
		final boolean isActive = true;
		final LocalDateTime lastLogin = LocalDateTime.now();
		final String password = "P4ssword";
		final String token = "token";

		final User newUser = new User();
				newUser.setName(name);
				newUser.setEmail(email);
				newUser.setPassword(password);
				newUser.setPhones(phones);
				
		final User savedUser = new User();
				savedUser.setId(USER_ID);
				savedUser.setName(name);
				savedUser.setEmail(email);
				savedUser.setPhones(phones);
				savedUser.setCreated(created);
				savedUser.setActive(isActive);
				savedUser.setLastLogin(lastLogin);
				savedUser.setPassword(password);

		given(userDAO.findUserByEmail(newUser.getEmail())).willReturn(Optional.empty());
		given(userDAO.save(newUser)).willReturn(savedUser);
		given(jwtToken.getJWTToken(USER_ID)).willReturn("token");
		given(userDAO.updateTokenById(token, USER_ID)).willReturn(1);
		given(passwordEncoder.encode(newUser.getPassword())).willReturn(password);

		// when
		User userResult = userService.save(newUser);

		// then
		assertThat(newUser.getName()).isEqualTo(name);
		assertThat(newUser.getEmail()).isEqualTo(email);
		assertThat(newUser.getPassword()).isEqualTo(password);
		assertThat(newUser.getPhones().size()).isEqualTo(1);

		assertThat(userResult.getName()).isEqualTo(name);
		assertThat(userResult.getEmail()).isEqualTo(email);
		assertThat(userResult.getPassword()).isEqualTo(password);
		assertThat(userResult.getPhones().size()).isEqualTo(1);
		assertThat(userResult.getCreated()).isEqualTo(created);
		assertThat(userResult.getLastLogin()).isEqualTo(lastLogin);
		assertThat(userResult.isActive()).isEqualTo(isActive);
		assertThat(userResult.getToken()).isEqualTo(token);
	}

}
