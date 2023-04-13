package com.packetsending_system_springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.Container;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Long> {
	
	//Az összes csomag automata lekérése az adatbázisból.
	@Override
	public List<Container> findAll();
	
	//Csomag automata keresése id alapján.
	@Override
	public Optional<Container> findById(Long id);
	
	//Csomag automata keresése város alapján
	public Container findByCity(String city);
	
	//Csomag automata keresése ip cím alapján.
	public Container findByIpAddress(String ipAddress);
	
	//Összes csomag automata lekérése, kivéve az amelyik id-je megegyezik a paraméterben kapott id-vel.
	public List<Container> findByIdNot(Long id);
	

}
