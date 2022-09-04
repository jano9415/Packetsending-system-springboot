package com.packetsending_system_springboot.service;

import com.packetsending_system_springboot.domain.Role;

public interface RoleService {

	//Szerepkör keresése id szerint.
	public Role findById(long id);
	
	//Szerepkör keresése a szerepkör neve szerint.
	public Role findByRoleName(String roleName);
}
