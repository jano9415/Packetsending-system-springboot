package com.packetsending_system_springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


	//Keresés emaicím alapján.
	public User findByEmailAddress(String emailAddress);
	

}
