package com.packetsending_system_springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Box {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int maxWidth;
	
	private int maxHeight;
	
	private int maxLength;
	
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
	
	
	
}
