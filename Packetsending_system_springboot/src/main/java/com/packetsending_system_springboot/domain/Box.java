package com.packetsending_system_springboot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Box {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int maxWidth;
	
	private int maxHeight;
	
	private int maxLength;
	
	
	//Kétoldali kapcsolat a Package és a Box között
	//Az idegen kulcs a Package táblában van. Ő a birtokos.
	//Egy box objektum több csomagot tartalmaz.
	@OneToMany(mappedBy = "box")
	private Set<Package> packages = new HashSet<Package>();
	
	//packages_in_container kapcsolótábla
	//a package osztály a birtokos
	/*@ManyToMany(mappedBy = "boxes")
	private Set<Package> packages = new HashSet<Package>();
	
	//Próba
	@ManyToMany(mappedBy = "boxes")
	private Set<Container> containers = new HashSet<Container>();*/
	
	public Box() {
	}
	

	public Box(int maxWidth, int maxHeight, int maxLength) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.maxLength = maxLength;
	}


	public Long getId() {
		return id;
	}


	public int getMaxWidth() {
		return maxWidth;
	}


	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}


	public int getMaxHeight() {
		return maxHeight;
	}


	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}


	public int getMaxLength() {
		return maxLength;
	}


	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}


	/*public Set<Package> getPackages() {
		return packages;
	}


	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}


	public Set<Container> getContainers() {
		return containers;
	}


	public void setContainers(Set<Container> containers) {
		this.containers = containers;
	}*/
	
	
	
	
	
	
	
}
