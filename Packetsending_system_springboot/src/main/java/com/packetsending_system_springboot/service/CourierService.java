package com.packetsending_system_springboot.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.packetsending_system_springboot.domain.Courier;

public interface CourierService {

	//Futár keresése id alapján
	public Courier findById(long id);
	
	//Futár keresése egyedi azonosítója alapján
	public Courier findByUniqueCourierId(String uniqueCourierId);
	
	public void loadCourierByUniqueCourierId(String uniqueCourierId);
	
	//public Courier getActualLoggedInCourier();
}
