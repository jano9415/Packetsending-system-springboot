package com.packetsending_system_springboot.domain;

public class Container {
	
	private Long id;
	
	private int postCode;
	
	private String city;
	
	private String address;
	
	private int amountOfBoxes;
	
	

	public Container() {

	}
		

	public Container(Long id, int postCode, String city, String address, int amountOfBoxes) {
		this.id = id;
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.amountOfBoxes = amountOfBoxes;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	

}
