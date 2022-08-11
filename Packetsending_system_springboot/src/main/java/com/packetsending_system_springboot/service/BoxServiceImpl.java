package com.packetsending_system_springboot.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.repository.BoxRepository;

@Service
public class BoxServiceImpl implements BoxService {

	private BoxRepository boxRepository;

	@Autowired
	public void setBoxRepository(BoxRepository boxRepository) {
		this.boxRepository = boxRepository;
	}

	//Doboz keresése id alapján.
	@Override
	public Box findById(long id) {
		return boxRepository.findById(id).get();
	}

	//Összes doboz lekérése
	@Override
	public List<Box> findAll() {
		return boxRepository.findAll();
	}
	
	
	
	
}
