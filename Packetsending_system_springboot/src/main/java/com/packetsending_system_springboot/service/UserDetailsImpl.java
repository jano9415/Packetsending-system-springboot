package com.packetsending_system_springboot.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.packetsending_system_springboot.domain.Courier;
import com.packetsending_system_springboot.domain.User;

public class UserDetailsImpl implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	private User user;
	private Courier courier;
	
	public UserDetailsImpl(User user , Courier courier) {
		this.user = user;
		this.courier = courier;
	}


	//Szerepkörök
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		if(user != null) {
			authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
			System.out.println("A felhasználó szerepköre: " + user.getRole().getRoleName());
		}
		else {
			authorities.add(new SimpleGrantedAuthority(courier.getRole().getRoleName()));
			System.out.println("A futár szerepköre: " + courier.getRole().getRoleName());
		}

		return authorities;
	}

	//Jelszó a user vagy courier objektumból.
	@Override
	public String getPassword() {
	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    if(user != null) {
	    	return encoder.encode(user.getPassword());
	    }
	    else {
	    	return encoder.encode(courier.getPassword());
	    }
	    
	}

	//Email cím a user objektumból vagy egyedi futár azonosító a courier objektumból.
	@Override
	public String getUsername() {
		if(user != null) {
			return user.getEmailAddress();
		}
		else {
			return courier.getUniqueCourierId();
		}
		
	}

	//Lejárt-e az account
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//Zárolva van-e az account
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Lejárt-e a jelszó
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//Felhasználó engedélyezve van-e
	@Override
	public boolean isEnabled() {
		if(user != null) {
			return user.isEnabled();
		}
		else {
			return true;
		}
		
	}

}
