package com.packetsending_system_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.domain.Courier;
import com.packetsending_system_springboot.domain.Package;

@Repository
public interface PackageRepository extends CrudRepository<Package, Long> {

	//Keresés "a csomagot elszállították-e már" és feladási hely szerint
	public List<Package> findAllByShippingFromAndPackageIsShipped(Container shippingFrom, boolean packageIsShipped);
	
	//Keresés szállítási hely szerint
	public List<Package> findAllByShippingToAndCourier(Container shippingTo, Courier courier);
	
	//Keresés egyedi csomagazonosító szerint
	public Package findByUniquePackageId(String uniquePackageId);
	
	//Keresés egyedi csomagazonosító szerint
	@Query(value = "select p.uniquePackageId from Package p where p.uniquePackageId=:uniquePackageId")
	public String findByUniquePackageIdOnlyString(@Param("uniquePackageId") String uniquePackageId);

	
	
}
