package com.contact.management.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.management.dto.ContactDetailsDto;
import com.contact.management.response.ApiResponse;
import com.contact.management.service.ContactDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Contact Details Controller")
@RestController
@RequestMapping("/rest/contact")
public class ContactController {

	Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactDetailsService contactDetailsService;

	@ApiOperation(value = "Creates new Contact Details")
	@PostMapping("/create-user-contact-details")
	public ResponseEntity<ApiResponse> createUserContactDetails(
			@RequestBody @Valid ContactDetailsDto contactDetailsDto) {
		logger.info("Inside createUserContactDetails method");
		return contactDetailsService.createStudent(contactDetailsDto);

	}

	@ApiOperation(value = "Update Contact Details")
	@PutMapping("/auth/update-user-contact-details")
	public ResponseEntity<ApiResponse> updateUserContactDetails(
			@RequestBody @Valid ContactDetailsDto contactDetailsDto) {
		logger.info("Inside updateUserContactDetails method");
		return contactDetailsService.updateUserContactDetails(contactDetailsDto);

	}

	@ApiOperation(value = "Fetches Contact Details of user on basis of emailId provided")
	@GetMapping("/auth/{email}")
	public ResponseEntity<ApiResponse> fetchesUserContactDetails(@PathVariable String email) {
		logger.info("Inside fetchesUserContactDetails method");
		return contactDetailsService.fetchesUserContactDetails(email);

	}

	@ApiOperation(value = "Delete Contact Details of user on basis of emailId provided")
	@DeleteMapping("/auth/{email}")
	public ResponseEntity<ApiResponse> deleteUserContactDetails(@PathVariable String email) {
		logger.info("Inside deleteUserContactDetails method");
		return contactDetailsService.deleteUserContactDetails(email);

	}
}
