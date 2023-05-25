package com.contact.management.service;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.contact.management.dto.ContactDetailsDto;
import com.contact.management.model.ContactDetails;
import com.contact.management.repository.ContactDetailsRepository;
import com.contact.management.response.ApiResponse;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

	Logger logger = LoggerFactory.getLogger(ContactDetailsServiceImpl.class);

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ApiResponse> createStudent(@Valid ContactDetailsDto contactDetailsDto) {
		ContactDetails contactDetails = null;
		ApiResponse apiresponse = new ApiResponse();
		logger.info("Inside createStudent method in ContactDetailsServiceImpl file");
		try {
			if (!StringUtils.isEmpty(contactDetailsDto.getFirstName())
					&& !StringUtils.isEmpty(contactDetailsDto.getLastName())
					&& !StringUtils.isEmpty(contactDetailsDto.getEmail())) {
				contactDetails = new ContactDetails();
				contactDetails.setFirstName(contactDetailsDto.getFirstName());
				contactDetails.setLastName(contactDetailsDto.getLastName());
				contactDetails.setEmail(contactDetailsDto.getEmail());
				contactDetails.setPassword(contactDetailsDto.getPassword());
				String encodedPassword = passwordEncoder.encode(contactDetailsDto.getPassword());
				contactDetails.setEncryptedPassword(encodedPassword);
				contactDetails.setPhoneNumber(contactDetailsDto.getPhoneNumber());
				contactDetailsRepository.save(contactDetails);
				apiresponse.setMessage("Contact Details created successfully");
				apiresponse.setResponseCode(2000);
				apiresponse.setObject(contactDetails);
			}else {
				apiresponse.setMessage("FirstName, LastName and Email cannot be empty");
				apiresponse.setResponseCode(2000);
			}
		} catch (Exception ex) {
			apiresponse.setMessage(ex.getMessage());
			apiresponse.setResponseCode(5000);
			logger.error("Some exception occured Inside createStudent method with reason : " + ex.getMessage());
			return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse> updateUserContactDetails(ContactDetailsDto contactDetailsDto) {
		ApiResponse apiresponse = null;
		ContactDetails contactDetails = null;
		try {
			Optional<ContactDetails> optionalContactDetails = contactDetailsRepository.findByEmail(contactDetailsDto.getEmail());
			if (optionalContactDetails.isPresent()) {
				contactDetails = optionalContactDetails.get();
				contactDetails.setFirstName(contactDetailsDto.getFirstName() != null ? contactDetailsDto.getFirstName()
						: contactDetails.getFirstName());
				contactDetails.setLastName(contactDetailsDto.getLastName() != null ? contactDetailsDto.getLastName()
						: contactDetails.getLastName());
				contactDetails.setEmail(contactDetailsDto.getEmail() != null ? contactDetailsDto.getEmail()
						: contactDetails.getEmail());
				contactDetails.setPhoneNumber(contactDetailsDto.getPhoneNumber() != null ? contactDetailsDto.getPhoneNumber()
						: contactDetails.getPhoneNumber());
				contactDetails.setPassword(contactDetailsDto.getPassword() != null ? contactDetailsDto.getPassword()
						: contactDetails.getPassword());
				String encodedPassword = passwordEncoder.encode(contactDetailsDto.getPassword());
				contactDetails.setEncryptedPassword(encodedPassword);
				contactDetailsRepository.save(contactDetails);
				apiresponse = new ApiResponse();
				apiresponse.setMessage("Contact Details updated successfully");
				apiresponse.setResponseCode(2000);
				apiresponse.setObject(contactDetails);
			} else {
				apiresponse = new ApiResponse();
				apiresponse.setResponseCode(2002);
				apiresponse.setMessage("Contact Details doesn't exists");
			}
		} catch (Exception ex) {
			apiresponse.setMessage(ex.getMessage());
			apiresponse.setResponseCode(5000);
		}
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse> fetchesUserContactDetails(String email) {
		ApiResponse apiresponse = null;
		try {
			boolean isExists = contactDetailsRepository.existsByEmail(email);
			if (!isExists) {
				apiresponse = new ApiResponse();
				apiresponse.setMessage("Contact Details Doesn't exists");
				apiresponse.setResponseCode(2002);

			} else {
				Optional<ContactDetails> contactDetails = contactDetailsRepository.findByEmail(email);
				apiresponse = new ApiResponse();
				apiresponse.setObject(contactDetails.get());
				apiresponse.setResponseCode(2000);
				apiresponse.setMessage("Contact Details fetched successfully");
			}

		} catch (Exception ex) {
			apiresponse.setMessage(ex.getMessage());
			apiresponse.setResponseCode(5000);
			return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse> deleteUserContactDetails(String email) {
		ApiResponse apiresponse = null;
		try {
			Optional<ContactDetails> contactDetails = contactDetailsRepository.findByEmail(email);
			if (!contactDetails.isPresent()) {
				apiresponse = new ApiResponse();
				apiresponse.setResponseCode(2000);
				apiresponse.setMessage("Contact Details Not Found for emailId : " + email);
				return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
			}
			contactDetailsRepository.delete(contactDetails.get());
			apiresponse = new ApiResponse();
			apiresponse.setResponseCode(2000);
			apiresponse.setMessage("Contact Details deleted successfully");
			return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
		} catch (Exception ex) {
			apiresponse.setMessage(ex.getMessage());
			apiresponse.setResponseCode(5000);
			return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
