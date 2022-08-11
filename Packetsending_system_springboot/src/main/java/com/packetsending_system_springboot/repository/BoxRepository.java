package com.packetsending_system_springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, Long> {

	//Összes doboz lekérése
	@Override
	public List<Box> findAll();
}
