package com.packetsending_system_springboot.domain;

public class Box {

	private Long id;
	
	private int maxWidth;
	
	private int maxHeight;
	
	private int maxLength;
	
	public Box() {
	}
	

	public Box(Long id, int maxWidth, int maxHeight, int maxLength) {
		this.id = id;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.maxLength = maxLength;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
