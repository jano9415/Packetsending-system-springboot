package com.packetsending_system_springboot.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.domain.Package;

public interface PackageService {

	//Csomagküldés. Csomag mentése az adatbázisban.
	public void savePackage(Package actualPackage);
	
	//Csomag keresése id alapján.
	public Package findById(long id);
	
	public List<Package> findAllByshippingFromAndpackageIsShipped(Container shippingFrom, boolean packageIsShipped);
	
	//Csomag keresése egyedi csomagazonosító szerint
	public Package findByUniquePackageId(String uniquePackageId);
	
	//Csomag keresése egyedi csomagazonosító szerint
	public String findByUniquePackageIdOnlyString(String uniquePackageId);
}
