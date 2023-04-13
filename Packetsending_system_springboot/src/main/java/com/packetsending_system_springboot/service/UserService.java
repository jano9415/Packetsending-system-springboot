package com.packetsending_system_springboot.service;

import java.util.Optional;

import com.packetsending_system_springboot.domain.User;

public interface UserService {

	//Regisztráció. Felhasználó mentése az adatbázisba.
	public void saveUser(User user);
	
	//Keresés emailcím alapján.
	public User findByEmailAddress(String emailAddress);
	
	//Keresés id alapján
	public User findById(long id);
	
	//Keresés aktivációs kód szerint.
	public User findByActivationCode(String activationCode);
	
	//Felhasználó aktiválása.
	public String userActivation(String activationCode);
	
	
	
}
