package com.packetsending_system_springboot.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Courier {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String uniqueCourierId;
	
	private String password;
	
	//packages_during_shipping kapcsolótábla
	//a Package osztály a birtokos
	@OneToMany(mappedBy = "courier")
	private Set<Package> packages;
	
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

	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}
	
	
	
	
	

}
