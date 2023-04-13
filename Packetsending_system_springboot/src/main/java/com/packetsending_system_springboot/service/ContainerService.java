package com.packetsending_system_springboot.service;

import java.util.List;
import java.util.Optional;

import com.packetsending_system_springboot.domain.Container;

public interface ContainerService {
	
	//Az összes csomag automata lekérése az adatbázisból.
	public List<Container> findAll();
	
	//Csomag automata keresése id alapján.
	public Container findById(long id);
	
	//Csomag automata keresése város alapján
	public Container findByCity(String city);
	
	//Csomag automata keresése ip cím alapján.
	public Container findByIpAddress(String ipAddress);
	
	//Összes csomag automata lekérése, kivéve az amelyik id-je megegyezik a paraméterben kapott id-vel.
	public List<Container> findByIdNot(Long id);

}
