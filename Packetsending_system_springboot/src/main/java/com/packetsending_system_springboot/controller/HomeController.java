package com.packetsending_system_springboot.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.logging.log4j.message.Message;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.packetsending_system_springboot.config.SecurityConfig;
import com.packetsending_system_springboot.domain.Box;
import com.packetsending_system_springboot.domain.Container;
import com.packetsending_system_springboot.domain.Package;
import com.packetsending_system_springboot.domain.User;
import com.packetsending_system_springboot.service.BoxService;
import com.packetsending_system_springboot.service.ContainerService;
import com.packetsending_system_springboot.service.CourierService;
import com.packetsending_system_springboot.service.CourierServiceImpl;
import com.packetsending_system_springboot.service.PackageService;
import com.packetsending_system_springboot.service.RoleService;
import com.packetsending_system_springboot.service.UserDetailsImpl;
import com.packetsending_system_springboot.service.UserService;
import com.packetsending_system_springboot.service.UserServiceImpl;
import com.zaxxer.hikari.util.ClockSource.MillisecondClockSource;


@Controller
public class HomeController {
	
	private UserService userService;
	private PackageService packageService;
	private ContainerService containerService;
	private BoxService boxService;
	private CourierService courierService;
	private RoleService roleService;
	private long actualContainerId = 2;
	
		
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
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired 
	private HttpServletRequest request;
	
	

	//Főoldal megjelenítése
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("actualContainer", containerService.findById(actualContainerId).getPostCode() + " " + containerService.findById(actualContainerId).getCity()
				+ " " + containerService.findById(actualContainerId).getAddress());
		System.out.println(request.getRemoteAddr() + "	" + request.getRemoteUser() + "	 " + request.getRemotePort() + "	" + request.getRemoteHost());
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
		if(containerService.findById(actualContainerId).getPackages().size() == 9) {
			//model.addAttribute("actualContainerFull", "Sajnáljuk, ez az automata tele van. Nem tudsz csomagot feladni.");
			model.addAttribute("actualContainerFull", "	<div class=\"warning-msg\" >\r\n"
					+ "					<span>&#9888;</span><span>Sajnáljuk, ez az automata tele van. Nem tudsz csomagot feladni.</span>\r\n"
					+ "				</div>");
		}
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
	public String containerfilling(Model model, Principal principal)
	{
		int packagesNumberOfCourier = 0;
		
		//model.addAttribute("packages", courierService.findById(CourierServiceImpl.actualLoggedInCourier.getId()).getPackages());
		model.addAttribute("packages", courierService.findByUniqueCourierId(principal.getName()).getPackages());
		
		/*for(Package actualPackage : courierService.findById(CourierServiceImpl.actualLoggedInCourier.getId()).getPackages()) {
			if(actualPackage.getShippingTo().getId() == actualContainerId) {
				packagesNumberOfCourier++;
			}
		}*/
		
		for(Package actualPackage : courierService.findByUniqueCourierId(principal.getName()).getPackages()) {
			if(actualPackage.getShippingTo().getId() == actualContainerId) {
				packagesNumberOfCourier++;
			}
		}
		model.addAttribute("packagesOfCourier", packagesNumberOfCourier + ". csomagod van ehhez az automatához.");
		return "containerfilling";
	}
	
	//Automata kiürítése oldal megjelenítése.
	//Minden csomag megjelenítése, ami az automatában van.
	@RequestMapping("/containeremptying")
	public String containeremptying(Model model)
	{
		int packagesWaitingShippingNumber = 0;
		
		model.addAttribute("packages", containerService.findById(actualContainerId).getPackages());
		for(Package actualPackage : containerService.findById(actualContainerId).getPackages()) {
			if(actualPackage.isPackageIsShipped() == false) {
				packagesWaitingShippingNumber++;
			}
		}
		model.addAttribute("packagesWaitingShipping", "Az elszállításra váró csomagok száma: " + packagesWaitingShippingNumber + " db.");
		return "containeremptying";
	}
	
	//Regisztráció.
	//Minden mező kitöltése kötelező.
	//Email cím és jelszó ellenőrzése.
	//Felhasználó mentése a user táblába.
	@PostMapping("/signupform")
	public String signupForm(@ModelAttribute User filledUser, Model model) {
		int errorNumber = 0;
		
		
		if(userService.findByEmailAddress(filledUser.getEmailAddress()) != null) {
			errorNumber++;
			//model.addAttribute("emailAlredyExist", "A megadott email címmel már regisztráltak.");
			  model.addAttribute("emailAlredyExist", "				<div class=\"warning-msg\">\r\n"
			  		+ "					<span>&#9888;</span><span>A megadott email címmel már regisztráltak.</span>\r\n"
			  		+ "				</div>");
		}
		
		if(passwordValidation(filledUser.getPassword()) == false) {
			/*model.addAttribute("passwordIsWeak", "Jelszó túl gyenge. Minimum 8 maximum 20 karakter."
					+ "Tartalmaznia kell legalább egy számot, legalább egy kisbetűt, legalább egy nagybetűt és legalább egy "
					+ "speciális karaktert az alábbiak közül: @#$%^&-+=()"
					+ "Nem tartalmazhat szóközt.");*/
			model.addAttribute("passwordIsWeak", "				<div class=\"warning-msg\">\r\n"
					+ "					<span>&#9888;</span><span>Jelszó túl gyenge. Minimum 8 maximum 20 karakter.\r\n"
					+ "					 Tartalmaznia kell legalább egy számot, legalább egy kisbetűt, legalább egy nagybetűt és legalább egy \r\n"
					+ "					 speciális karaktert az alábbiak közül: @#$%^&-+=()\r\n"
					+ "					 Nem tartalmazhat szóközt.</span>\r\n"
					+ "				</div>");
			errorNumber++;
		}
		
		if(errorNumber == 0) {
			filledUser.setRole(roleService.findByRoleName("user"));
			userService.saveUser(filledUser);
		}
		

		return "signup";
	}
	
	//Csomagküldés. Csomag mentése a package táblába.
	@PostMapping("/packetsendingform")
	public String packetSendingForm(@ModelAttribute Package filledPackage, @RequestParam String selectedShippingToCity, Model model, Principal principal ) {
		
		int shippingIsNotPossibleCount = 0;
		Box freeBoxInActualContainer = checkActualContainerFull(filledPackage);
		Box freeBoxInSelectedContainer = checkSelectedContainerFull(selectedShippingToCity, filledPackage);
		
		if(freeBoxInActualContainer == null) {
			//model.addAttribute("actualContainerFull", "Sajnáljuk, ez az automata tele van. Nem tudsz csomagot feladni.");
			model.addAttribute("actualContainerFull", "	<div class=\"warning-msg\" >\r\n"
					+ "					<span>&#9888;</span><span>Sajnáljuk, ez az automata tele van. Nem tudsz csomagot feladni.</span>\r\n"
					+ "				</div>");
			shippingIsNotPossibleCount++;
		}
		
		if(freeBoxInSelectedContainer == null) {
			//model.addAttribute("selectedContainerFull", "Sajnáljuk, a kiválasztott automata tele van. Nem tudsz csomagot feladni.");
			model.addAttribute("selectedContainerFull", "	<div class=\"warning-msg\" >\r\n"
					+ "					<span>&#9888;</span><span>Sajnáljuk, a kiválasztott automata tele van. Nem tudsz csomagot feladni.</span>\r\n"
					+ "				</div>");
			shippingIsNotPossibleCount++;
		}
		
		if(shippingIsNotPossibleCount == 0) {

			//Csomag objektum feltöltése
			filledPackage.setUniquePackageId(generateRandomString());
			filledPackage.setShippingFrom(containerService.findById(actualContainerId));
			filledPackage.setShippingTo(containerService.findByCity(selectedShippingToCity));
			//filledPackage.setUser(userService.findById(UserServiceImpl.actualLoggedInUser.getId()));
			filledPackage.setUser(userService.findByEmailAddress(principal.getName()));
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
			//model.addAttribute("boxNumber", "Tedd be a csomagodat a(z) " + filledPackage.getBox().getId() + ". rekeszbe.");
			model.addAttribute("boxNumber", "	<div class=\"info-msg\" >\r\n"
					+ "					<span>&#8505;</span><span> Tedd be a csomagodat a(z) " + filledPackage.getBox().getId() + ". rekeszbe.</span>\r\n"
					+ "				</div>");

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
	    
	    if(packageService.findByUniquePackageId(randomString) != null) {
	    	generateRandomString();
	    }
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
					return box;
				}
			}
		}
		return null;
	}

	//Az aktuális automata kiürítése. A szállítandó csomagok átkerülnek a futárhoz.
	//Csomag és futár kapcsolat létrehozása a packages_during_shipping táblában.
	@PostMapping("/containeremptyingform")
	public String containerEmptyingForm(Model model, Principal principal) {
		String message = "";
		for(Package actualPackage : containerService.findById(actualContainerId).getPackages()) {
			if(actualPackage.getShippingFrom().getId() == actualContainerId) {
				
				message += "Vedd ki a(z) " + actualPackage.getUniquePackageId() + " számú csomagot a(z) " + actualPackage.getBox().getId() + ". rekeszből.\n";
				
				actualPackage.setContainers(null);
				actualPackage.setBox(null);
				//actualPackage.setCourier(courierService.findById(CourierServiceImpl.actualLoggedInCourier.getId()));
				actualPackage.setCourier(courierService.findByUniqueCourierId(principal.getName()));
				//courierService.findById(CourierServiceImpl.actualLoggedInCourier.getId()).getPackages().add(actualPackage);
				courierService.findByUniqueCourierId(principal.getName()).getPackages().add(actualPackage);
				packageService.savePackage(actualPackage);
			}
		}
		model.addAttribute("packagesFromContainerMessage", message);
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
	public String containerFillingForm(Model model, Principal principal) {
		String message = "";
		
		//for(Package actualPackage : courierService.findById(CourierServiceImpl.actualLoggedInCourier.getId()).getPackages()) {
			for(Package actualPackage : courierService.findByUniqueCourierId(principal.getName()).getPackages()) {
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
				
				message += "Tedd be a(z) " + actualPackage.getUniquePackageId() + " számú csomagot a(z) " + actualPackage.getBox().getId() + ". rekeszbe.\n";

			}
		}
		model.addAttribute("packagesToContainerMessage", message);
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

	//Csomagátvétel. Egyedi csomag azonosító bekérése. Csomag keresése az adatbázisban.
	//packages_in_container tábla frissítése
	//Értesítés küldése a sikeres átvételről.
	@PostMapping("/packettakingform")
	public String packetTakingForm(@RequestParam String uniquePackageId, Model model) {
		Package actualPackage = packageService.findByUniquePackageId(uniquePackageId);
		
		if(actualPackage != null && actualPackage.isPackageIsShipped() && actualPackage.getShippingTo().getId() == actualContainerId && actualPackage.isPackageIsTaken() == false) {
			//model.addAttribute("packageTakingMessage", "Vedd ki a csomagot a " + actualPackage.getBox().getId() + ". rekeszből.");
			model.addAttribute("packageTakingMessage", "<div class=\"info-msg\" >\r\n"
					+ "					<span>&#8505;</span><span> Vedd ki a csomagot a(z) " + actualPackage.getBox().getId() + ". rekeszből.</span>\r\n"
					+ "				</div>"); 
			actualPackage.setPackageIsTaken(true);
			
			long millis = System.currentTimeMillis();
			actualPackage.setTakingDate(new Date(millis));
			actualPackage.setTakingTime(new Time(millis));
			actualPackage.setBox(null);
			
			actualPackage.setContainers(null);
			packageService.savePackage(actualPackage);
			
			packageIsTakenNotice();
			
		}
		else{
			//model.addAttribute("packageTakingMessage", "Csomag nem található. Add meg az azonosítót újra.");
			model.addAttribute("packageTakingMessage", "<div class=\"error-msg\" >\r\n"
					+ "					<span>&#9746;</span><span> Csomag nem található. Add meg az azonosítót újra.</span>\r\n"
					+ "				</div>"); 
		}
		return "packettaking";
	}

	//Értesítés a sikeres átvételről. Email küldése a csomag átvevőjének.
	public void packageIsTakenNotice() {
		
	}

	//Megadott jelszó ellenőrzése.
	public boolean passwordValidation(String password) {
		//^ represents starting character of the string.
		//(?=.*[0-9]) represents a digit must occur at least once.
		//(?=.*[a-z]) represents a lower case alphabet must occur at least once.
		//(?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
		//(?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
		//(?=\\S+$) white spaces don’t allowed in the entire string.
		//.{8, 20} represents at least 8 characters and at most 20 characters.
		//$ represents the end of the string.
		
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        Pattern p = Pattern.compile(regex);
  
        Matcher m = p.matcher(password);
  
        return m.matches();
	}
	
}




