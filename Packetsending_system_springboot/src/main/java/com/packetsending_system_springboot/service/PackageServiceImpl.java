package com.packetsending_system_springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.domain.Package;
import com.packetsending_system_springboot.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService {

	private PackageRepository packageRepository;

	@Autowired
	public void setPackageRepository(PackageRepository packageRepository) {
		this.packageRepository = packageRepository;
	}

	//Csomagküldés. Csomag mentése az adatbázisban.
	@Override
	public void savePackage(Package actualPackage) {
		packageRepository.save(actualPackage);
		
	}

	//Csomag keresése id alapján.
	@Override
	public Package findById(long id) {
		return packageRepository.findById(id).get();
	}

	@Override
	public List<Package> findAllByshippingFromAndpackageIsShipped(Container shippingFrom, boolean packageIsShipped) {
		return packageRepository.findAllByShippingFromAndPackageIsShipped(shippingFrom, packageIsShipped);
	}


	
	


	
	
	
	
	
}
