package com.packetsending_system_springboot.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Container {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int postCode;
	
	private String city;
	
	private String address;
	
	private int amountOfBoxes;
	
	@OneToMany(mappedBy = "shippingFrom")
	private Set<Package> packagesFrom;
	
	@OneToMany(mappedBy = "shippingTo")
	private Set<Package> packagesTo;
	
	

	public Container() {

	}
		

	public Container(int postCode, String city, String address, int amountOfBoxes) {
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.amountOfBoxes = amountOfBoxes;
	}


	public Long getId() {
		return id;
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

	public int getAmountOfBoxes() {
		return amountOfBoxes;
	}

	public void setAmountOfBoxes(int amountOfBoxes) {
		this.amountOfBoxes = amountOfBoxes;
	}


	public Set<Package> getPackagesFrom() {
		return packagesFrom;
	}


	public void setPackagesFrom(Set<Package> packagesFrom) {
		this.packagesFrom = packagesFrom;
	}


	public Set<Package> getPackagesTo() {
		return packagesTo;
	}


	public void setPackagesTo(Set<Package> packagesTo) {
		this.packagesTo = packagesTo;
	}
	
	
	
	
	

}
