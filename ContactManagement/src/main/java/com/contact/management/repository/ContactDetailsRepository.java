package com.contact.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.management.model.ContactDetails;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

	ContactDetails findByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email);

	Optional<ContactDetails> findByEmail(String email);

	boolean existsByEmail(String email);
}
