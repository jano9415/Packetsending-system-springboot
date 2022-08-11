package com.packetsending_system_springboot.service;



import java.util.List;

import com.packetsending_system_springboot.domain.Box;

public interface BoxService {

	//Doboz keresése id alapján.
	public Box findById(long id);
	
	//Összes doboz lekérése
	public List<Box> findAll();
}
