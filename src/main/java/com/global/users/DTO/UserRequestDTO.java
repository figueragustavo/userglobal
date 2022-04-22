package com.global.users.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	
	private String name;

	@NotNull
	@Email
	private String email;
	@NotNull
	@Pattern(regexp ="^(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]).{8,12}")
	private String password;
	private List<PhoneDTO> phones;

}
