package com.globallogic.users.model;


import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {

	private String name;

	@NotNull
	@Email
	private String email;
	@NotNull
	@Pattern(regexp ="^(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]).{8,12}")
	private String password;
	private List<PhoneDTO> phones;
	
	/**
	 * @return the phones
	 */
	public List<PhoneDTO> getPhones() {
		return phones;
	}
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

//	/**
//	 * @return the phones
//	 */
//	public List<String> getPhones() {
//		return phones;
//	}
//
//	/**
//	 * @param phones the phones to set
//	 */
//	public void setPhones(List<String> phones) {
//		this.phones = phones;
//	}
}
