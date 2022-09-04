package com.packetsending_system_springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Courier;
import com.packetsending_system_springboot.domain.User;
import com.packetsending_system_springboot.repository.CourierRepository;
import com.packetsending_system_springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private CourierService courierService;
	//public static User actualLoggedInUser;

	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Autowired
	public void setCourierService(CourierService courierService) {
		this.courierService = courierService;
	}

	//Bejelentkezett felhasználó lekérése
	/*public User getActualLoggedInUser() {
		return actualLoggedInUser;
	}*/
	


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

	//Spring security loadUserByUsername függvény felül írása.
	//Ha a megadott felhasználói név 6 karakter hosszú, akkor egy futár próbál meg bejelentkezni. Futár keresése az adatbázisban
	//egyedi futár azonosítója alapján.
	//Ha a megadott felhasználói név nem 6 karakter hosszú, akkor egy regisztrált felhasználó próbál meg bejelentkezni. Felhasználó keresése az adatbázisban
	//email cím alapján.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.length() == 6) {
			//courierService.loadCourierByUniqueCourierId(username);
			//return new UserDetailsImpl(null , CourierServiceImpl.actualLoggedInCourier);
			courierService.loadCourierByUniqueCourierId(username);
			return new UserDetailsImpl(null , courierService.findByUniqueCourierId(username));
		}

		//actualLoggedInUser = findByEmailAddress(username);
		User actualLoggedInUser = findByEmailAddress(username);
		if(actualLoggedInUser == null) 
		{
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(actualLoggedInUser , null);
	}


	
	
	
	
	
}
