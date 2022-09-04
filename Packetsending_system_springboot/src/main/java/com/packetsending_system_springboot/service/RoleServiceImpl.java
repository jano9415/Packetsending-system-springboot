package com.packetsending_system_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Role;
import com.packetsending_system_springboot.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepository;

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	//Szerepkör keresése id szerint.
	@Override
	public Role findById(long id) {
		return roleRepository.findById(id).get();
	}

	//Szerepkör keresése a szerepkör neve szerint.
	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
	
	
	
	

}
