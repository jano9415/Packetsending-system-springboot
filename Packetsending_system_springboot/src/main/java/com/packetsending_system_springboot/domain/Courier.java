package com.packetsending_system_springboot.domain;

public class Courier {
	
	private Long id;
	
	private String uniqueCourierId;
	
	private String password;
	
	public Courier() {
		
	}

	public Courier(Long id, String uniqueCourierId, String password) {
		this.id = id;
		this.uniqueCourierId = uniqueCourierId;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueCourierId() {
		return uniqueCourierId;
	}

	public void setUniqueCourierId(String uniqueCourierId) {
		this.uniqueCourierId = uniqueCourierId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
