package com.packetsending_system_springboot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	//Kétoldali kapcsolat a container és a package között.
	//A package osztály a birtokos.
	//A package táblában van az idegen kulcs.
	//Egy conatiner tartalmaz több csomagot, amiket ott adtak fel.
	@OneToMany(mappedBy = "shippingFrom")
	private Set<Package> packagesFrom = new HashSet<Package>();
	
	//Kétoldali kapcsolat a container és a package között.
	//A package osztály a birtokos.
	//A package táblában van az idegen kulcs.
	//Egy conatiner tartalmaz több csomagot, amiket oda szállítottak.
	@OneToMany(mappedBy = "shippingTo")
	private Set<Package> packagesTo = new HashSet<Package>();
	
	//packages_in_container kapcsolótábla
	//a package osztály a birtokos
	@ManyToMany(mappedBy = "containers")
	private Set<Package> packages = new HashSet<Package>();
	
	//Próba
	/*@ManyToMany
	@JoinTable(
			name = "packages_in_container",
			joinColumns = {@JoinColumn(name = "container_id")},
			inverseJoinColumns = {@JoinColumn(name = "box_id")})
	private Set<Box> boxes = new HashSet<Box>();*/
	
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


	public Set<Package> getPackages() {
		return packages;
	}


	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}


	/*public Set<Box> getBoxes() {
		return boxes;
	}


	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}*/
	
	
	
	
	
	
	
	
	

}
