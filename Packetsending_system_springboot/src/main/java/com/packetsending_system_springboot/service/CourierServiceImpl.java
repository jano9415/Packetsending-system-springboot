package com.packetsending_system_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Courier;
import com.packetsending_system_springboot.repository.CourierRepository;

@Service
public class CourierServiceImpl implements CourierService {

	private CourierRepository courierRepository;

	@Autowired
	public void setCourierRepository(CourierRepository courierRepository) {
		this.courierRepository = courierRepository;
	}

	//Futár keresése id alapján
	@Override
	public Courier findById(long id) {
		return courierRepository.findById(id).get();
	}
	
	//Futár keresése egyedi azonosítója alapján.
	@Override
	public Courier findByUniqueCourierId(String uniqueCourierId) {
		return courierRepository.findByUniqueCourierId(uniqueCourierId);
	}





	
	
}
