package com.packetsending_system_springboot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String roleName;
	
	//Kétoldali kapcsolat a user és a role között.
	//A user osztály a birtokos. Az tartalmazza az idegen kulcsot.
	//Egy szerepkörhöz több felhasználó tartozik.
	@OneToMany(mappedBy = "role")
	private Set<User> users = new HashSet<User>();
	
	//Kétoldali kapcsolat a courier és a role között.
	//A courier osztály a birtokos. Az tartalmazza az idegen kulcsot.
	//Egy szerepkörhöz több futár tartozik.
	@OneToMany(mappedBy = "role")
	private Set<Courier> couriers = new HashSet<Courier>();
	
	public Role() {
		
	}

	public Role(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Courier> getCouriers() {
		return couriers;
	}

	public void setCouriers(Set<Courier> couriers) {
		this.couriers = couriers;
	}
	
	
	
	
	

}
