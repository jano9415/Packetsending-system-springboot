package com.packetsending_system_springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.repository.ContainerRepository;

@Service
public class ContainerServiceImpl implements ContainerService {

	private ContainerRepository containerRepository;
	
	@Autowired
	public void setContainerRepository(ContainerRepository containerRepository) {
		this.containerRepository = containerRepository;
	}

	//Az összes csomag automata lekérése az adatbázisból.
	@Override
	public List<Container> findAll() {
		return containerRepository.findAll();
	}

	//Csomag automata keresése id alapján.
	@Override
	public Container findById(long id) {
		return containerRepository.findById(id).get();
	}

	//Csomag automata keresése város alapján
	@Override
	public Container findByCity(String city) {
		return containerRepository.findByCity(city);
	}
	
	
	
	
	
	
}
