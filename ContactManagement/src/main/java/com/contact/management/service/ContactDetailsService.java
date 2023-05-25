package com.contact.management.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.contact.management.dto.ContactDetailsDto;
import com.contact.management.response.ApiResponse;

public interface ContactDetailsService {

	ResponseEntity<ApiResponse> createStudent(@Valid ContactDetailsDto contactDetailsDto);

	ResponseEntity<ApiResponse> updateUserContactDetails(ContactDetailsDto contactDetailsDto);

	ResponseEntity<ApiResponse> fetchesUserContactDetails(String email);

	ResponseEntity<ApiResponse> deleteUserContactDetails(String email);

}
