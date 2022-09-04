package com.packetsending_system_springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	//Szerepkör keresése a szerepkör neve szerint.
	public Role findByRoleName(String roleName);

}
