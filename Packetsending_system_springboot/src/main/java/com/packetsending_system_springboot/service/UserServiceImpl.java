package com.packetsending_system_springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.User;
import com.packetsending_system_springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//Regisztráció. Felhasználó mentése az adatbázisba.
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}
	
	//Keresés emailcím alapján.
	@Override
	public User findByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}

	//Keresés id alapján
	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}
	
	
	
	
	
	
}
