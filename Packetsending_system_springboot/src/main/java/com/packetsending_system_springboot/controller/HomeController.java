package com.packetsending_system_springboot.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.domain.Package;
import com.packetsending_system_springboot.domain.User;
import com.packetsending_system_springboot.service.BoxService;
import com.packetsending_system_springboot.service.ContainerService;
import com.packetsending_system_springboot.service.CourierService;
import com.packetsending_system_springboot.service.PackageService;
import com.packetsending_system_springboot.service.UserService;
import com.zaxxer.hikari.util.ClockSource.MillisecondClockSource;

@Controller
public class HomeController {
	
	private UserService userService;
	private PackageService packageService;
	private ContainerService containerService;
	private BoxService boxService;
	private CourierService courierService;
	private long actualContainerId = 5;
	
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
	
	@Autowired
	public void setContainerService(ContainerService containerService) {
		this.containerService = containerService;
	}
	
	@Autowired
	public void setBoxService(BoxService boxService) {
		this.boxService = boxService;
	}

	@Autowired
	public void setCourierService(CourierService courierService) {
		this.courierService = courierService;
	}

	//Főoldal megjelenítése
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	//Bejelentkezési oldal megjelenítése
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//Regisztrációs oldal megjelenítése
	//Üres user objektum küldése a signup form-nak.
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	//Csomagküldő oldal megjelenítése
	//Üres package objektum küldése a packetsending form-nak.
	@RequestMapping("/packetsending")
	public String packetSending(Model model) {
		model.addAttribute("package", new Package());
		return "packetsending";
	}
	
	//Csomagátvevő oldal megjelenítése
	@RequestMapping("/packettaking")
	public String packetTaking() {
		return "packettaking";
	}
	
	//Automata feltöltése oldal megjelenítése.
	//A futárnál lévő csomagok megjelenítése.
	@RequestMapping("/containerfilling")
	public String containerfilling(Model model)
	{
		model.addAttribute("packages",courierService.findById(1).getPackages());
		return "containerfilling";
	}
	
	//Automata kiürítése oldal megjelenítése.
	//Elszállítandó csomagok megjelenítése.
	@RequestMapping("/containeremptying")
	public String containeremptying(Model model)
	{
		model.addAttribute("packages", containerService.findById(actualContainerId).getPackages());
		return "containeremptying";
	}
	
	//Regisztráció. Felhasználó mentése a user táblába.
	@PostMapping("/signupform")
	public String signupForm(@ModelAttribute User filledUser) {
		if(userService.findByEmailAddress(filledUser.getEmailAddress()) != null) {
			System.out.println("Ilyen email cím már létezik.");
		}
		else {
			userService.saveUser(filledUser);
		}
		return "signup";
	}
	
	//Csomagküldés. Csomag mentése a package táblába.
	@PostMapping("/packetsendingform")
	public String packetSendingForm(@ModelAttribute Package filledPackage, @RequestParam String selectedShippingToCity ) {
		
		int shippingIsNotPossibleCount = 0;
		Box freeBoxInActualContainer = checkActualContainerFull(filledPackage);
		Box freeBoxInSelectedContainer = checkSelectedContainerFull(selectedShippingToCity, filledPackage);
		
		if(freeBoxInActualContainer == null) {
			System.out.println("Sajnáljuk, ez az automata tele van. Nem tudsz csomagot feladni.");
			shippingIsNotPossibleCount++;
		}
		
		if(freeBoxInSelectedContainer == null) {
			System.out.println("Sajnáljuk, a kiválasztott automata tele van. Nem tudsz csomagot feladni.");
			shippingIsNotPossibleCount++;
		}
		
		if(shippingIsNotPossibleCount == 0) {
			//Csomag objektum feltöltése értékekkel
			filledPackage.setUniquePackageId(generateRandomString());
			filledPackage.setShippingFrom(containerService.findById(actualContainerId));
			filledPackage.setShippingTo(containerService.findByCity(selectedShippingToCity));
			filledPackage.setUser(userService.findById(90));
			filledPackage.setPackageIsShipped(false);
			filledPackage.setPackageIsTaken(false);
			filledPackage.setBox(freeBoxInActualContainer);
			
			//Feladási dátum és idő beállítása
			long millis = System.currentTimeMillis();
			filledPackage.setSendingDate(new Date(millis));
			filledPackage.setSendingTime(new Time(millis));
			
			//Csomag és automata kapcsolat létrehozása a packages_in_container táblába
			filledPackage.getContainers().add(containerService.findById(actualContainerId));
			containerService.findById(actualContainerId).getPackages().add(filledPackage);
			
			packageService.savePackage(filledPackage);
			
			sendPackageNotice(filledPackage);
			
			System.out.println("Tedd be a csomagodat a(z) " + filledPackage.getBox().getId() + ". rekeszbe.");
		}


		

		return "packetsending";
	}
	
	//Random string generálása.
	public String generateRandomString() {
		
	    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";

	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

	    StringBuilder sb = new StringBuilder();

	    Random random = new Random();

	    int length = 5;

	    for(int i = 0; i < length; i++) {

	      int index = random.nextInt(alphaNumeric.length());

	      char randomChar = alphaNumeric.charAt(index);

	      sb.append(randomChar);
	    }

	    String randomString = sb.toString();
	    return randomString;
	}

	//A feladási automata rekeszeinek ellenőrzése. Ha mind tele van, akkor nem lehet csomagot feladni.
	public Box checkActualContainerFull(Package filledPackage) {
		Set<Box> boxesFull = new HashSet<Box>();
		
		for(Package p : containerService.findById(actualContainerId).getPackages())
		{
			boxesFull.add(p.getBox());
		}
		
		for(Box box : boxService.findAll()) {
			if(filledPackage.getWidth() < box.getMaxWidth() && filledPackage.getHeight() < box.getMaxHeight() && filledPackage.getLength() < box.getMaxLength()) {
				if(boxesFull.contains(box) == false) {
					return box;
				}
			}
		}
		return null;
	}
	
	//Az érkezési automata rekeszeinek ellenőrzése. Ha mind tele van, akkor nem lehet csomagot feladni.
	public Box checkSelectedContainerFull(String selectedShippingToCity, Package filledPackage) { 

		Set<Box> boxesFull = new HashSet<Box>();
		
		for(Package p : containerService.findByCity(selectedShippingToCity).getPackages())
		{
			boxesFull.add(p.getBox());
		}
		
		for(Box box : boxService.findAll()) {
			if(filledPackage.getWidth() < box.getMaxWidth() && filledPackage.getHeight() < box.getMaxHeight() && filledPackage.getLength() < box.getMaxLength()) {
				if(boxesFull.contains(box) == false) {
					System.out.println("A kiválasztott automatában van szabad hely.");
					return box;
				}
			}
		}
		return null;
	}

	//Az aktuális automata kiürítése. A szállítandó csomagok átkerülnek a futárhoz.
	//Csomag és futár kapcsolat létrehozása a packages_during_shipping táblában.
	@PostMapping("/containeremptyingform")
	public String containerEmptyingForm() {
		for(Package actualPackage : containerService.findById(actualContainerId).getPackages()) {
			if(actualPackage.getShippingFrom().getId() == actualContainerId) {
				actualPackage.setContainers(null);
				actualPackage.setBox(null);
				actualPackage.setCourier(courierService.findById(1));
				courierService.findById(1).getPackages().add(actualPackage);
				packageService.savePackage(actualPackage);
			}

		}
		return "containeremptying";
	}

	//Értesítés a csomag feladásáról. Email küldése a csomag átvevőjének.
	public void sendPackageNotice(Package actualPackage) {
		try {
			FileWriter fw = new FileWriter(actualPackage.getReceiverEmailAddress() + ".txt");
			
			fw.write("Csomagot adtak fel Önnek a quickpost rendszerben.\n");
			fw.write("A feladás helye: " + actualPackage.getShippingFrom().getPostCode() + " " + actualPackage.getShippingFrom().getCity()
					+ " " + actualPackage.getShippingFrom().getAddress() + "\n");
			fw.write("A feladó neve: " + actualPackage.getUser().getLastName() + " " + actualPackage.getUser().getFirstName() + "\n");
			fw.write("A csomag ára: " + actualPackage.getPrice() + " Ft\n");
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Az aktuális automata feltöltése. Ha a futárnál lévő csomagok érkezési címe megegyezik az aktuális automata címével és van szabad rekesz
	//a csomagok számára, akkor bekerülnek az automatába.
	//packages_during_shipping és packages_in_container táblák frissítése
	@PostMapping("/containerfillingform")
	public String containerFillingForm() {
		for(Package actualPackage : courierService.findById(1).getPackages()) {
			if(actualContainerId == actualPackage.getShippingTo().getId() && checkActualContainerFull(actualPackage) != null) {
				actualPackage.setCourier(null);
				actualPackage.setBox(checkActualContainerFull(actualPackage));
				containerService.findById(actualContainerId).getPackages().add(actualPackage);
				actualPackage.getContainers().add(containerService.findById(actualContainerId));
				actualPackage.setPackageIsShipped(true);
				
				long millis = System.currentTimeMillis();
				actualPackage.setShippingDate(new Date(millis));
				actualPackage.setShippingTime(new Time(millis));
				
				packageService.savePackage(actualPackage);
				
				packageIsShippedNotice(actualPackage);

			}
		}
		return "containerfilling";
	}

	//Értesítés a csomag megérkezéséről. Email küldése a csomag átvevőjének.
	public void packageIsShippedNotice(Package actualPackage) {
		try {
			FileWriter fw = new FileWriter(actualPackage.getReceiverEmailAddress() + "2" + ".txt");
			
			fw.write("A csomagja megérkezett.\n");
			fw.write("Itt tudja átvenni: " + actualPackage.getShippingTo().getPostCode() + " " + actualPackage.getShippingTo().getCity()
					+ " " + actualPackage.getShippingTo().getAddress() + "\n");
			fw.write("A csomag ára: " + actualPackage.getPrice() + "\n");
			fw.write("A nyitó kód: " + actualPackage.getUniquePackageId() + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




