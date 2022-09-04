package com.packetsending_system_springboot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@OneToMany(mappedBy = "courier" /*, fetch= FetchType.EAGER*/)
	private Set<Package> packages =  new HashSet<Package>();
	
	//Kétoldali kapcsolat a courier és a role között.
	//Ez az osztály a birtokos. Ez tartalmazza az idegen kulcsot.
	//Idegen kulcs a role tábla role_id attribútumára.
	//Egy futárnak egy szerepköre van.
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
	

}
