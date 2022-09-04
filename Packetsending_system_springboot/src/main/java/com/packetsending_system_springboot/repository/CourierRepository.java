package com.packetsending_system_springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {

	//Futár keresése egyedi azonosítója alapján
	public Courier findByUniqueCourierId(String uniqueCourierId);
}
