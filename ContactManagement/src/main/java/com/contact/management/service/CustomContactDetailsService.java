package com.contact.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contact.management.model.ContactDetails;
import com.contact.management.repository.ContactDetailsRepository;

@Service
public class CustomContactDetailsService implements UserDetailsService {

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<ContactDetails> contactDetails = contactDetailsRepository.findByEmail(email);
		CustomContactDetails customContactDetails = null;
		if (contactDetails.isPresent()) {
			customContactDetails = new CustomContactDetails();
			customContactDetails.setContactDetails(contactDetails.get());
		} else {
			throw new UsernameNotFoundException("User not exists with the following emailId : " + email);
		}
		return customContactDetails;
	}

}
