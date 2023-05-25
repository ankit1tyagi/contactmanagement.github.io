package com.contact.management.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDto {

	private String firstName;
	private String lastName;
	private String email;
	@NotNull(message = "phoneNumber cannot be null")
	private String phoneNumber;
	@NotNull(message = "password cannot be null")
	private String password;
}
