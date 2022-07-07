package com.packetsending_system_springboot.domain;

public class User {

	private Long id;
	
	private String emailAddress;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private int postCode;
	
	private String city;
	
	private String address;
	
	private String phoneNumber;
	
	public User() {
		
	}

	public User(Long id, String emailAddress, String password, String firstName, String lastName, int postCode,
			String city, String address, String phoneNumber) {
		this.id = id;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}
