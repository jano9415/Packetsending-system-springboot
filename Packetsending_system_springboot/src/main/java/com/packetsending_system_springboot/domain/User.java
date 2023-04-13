package com.packetsending_system_springboot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "registrateduser")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String emailAddress;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private int postCode;
	
	private String city;
	
	private String address;
	
	private String phoneNumber;
	
	private String activationCode;
	
	private boolean enabled;
	
	//Kétoldali kapcsolat a package és a user között.
	//A package osztály a birtokos.
	//Az idegen kulcs a package táblában van.
	//Egy regisztrált felhasználó rendelkezik több csomaggal.
	@OneToMany(mappedBy = "user")
	private Set<Package> packages = new HashSet<Package>();
	
	//Kétoldali kapcsolat a user és a role között.
	//Ez az osztály a birtokos. Ez tartalmazza az idegen kulcsot.
	//Idegen kulcs a role tábla role_id attribútumára.
	//Egy felhasználónak egy szerepköre van.
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	

	public User() {
		
	}

	public User(String emailAddress, String password, String firstName, String lastName, int postCode,
			String city, String address, String phoneNumber) {
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
	
	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
	
	
	
	
	
	
}
