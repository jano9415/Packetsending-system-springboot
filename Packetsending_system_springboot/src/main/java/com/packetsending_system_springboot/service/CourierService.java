package com.packetsending_system_springboot.service;

import com.packetsending_system_springboot.domain.Courier;

public interface CourierService {

	//Futár keresése id alapján
	public Courier findById(long id);
}
