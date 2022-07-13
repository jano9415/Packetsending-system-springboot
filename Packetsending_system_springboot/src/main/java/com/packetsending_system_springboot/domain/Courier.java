package com.packetsending_system_springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Courier {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String uniqueCourierId;
	
	private String password;
	
	public Courier() {
		
	}

	public Courier(String uniqueCourierId, String password) {
		this.uniqueCourierId = uniqueCourierId;
		this.password = password;
	}

	public Long getId() {
		return id;
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
