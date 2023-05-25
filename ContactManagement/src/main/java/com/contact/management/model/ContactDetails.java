package com.contact.management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "contact_details")
public class ContactDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -918498302992860943L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;

	@Column(unique=true, nullable=false)
	private String email;

	@Column(nullable=false)
	private String phoneNumber;
	
	@Column
	private String password;
	
	@Column
	@JsonIgnore
	private String encryptedPassword;
}
