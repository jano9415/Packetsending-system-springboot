package com.packetsending_system_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
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
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	//Csomagküldő oldal megjelenítése
	@RequestMapping("/packetsending")
	public String packetSending() {
		return "packetsending";
	}
	
	//Csomagátvevő oldal megjelenítése
	@RequestMapping("/packettaking")
	public String packetTaking() {
		return "packettaking";
	}
	
	//Automata feltöltése oldal megjelenítése
	@RequestMapping("/containerfilling")
	public String containerfilling()
	{
		return "containerfilling";
	}
	
	//Automata kiürítése oldal megjelenítése
	@RequestMapping("/containeremptying")
	public String containeremptying()
	{
		return "containeremptying";
	}

}
