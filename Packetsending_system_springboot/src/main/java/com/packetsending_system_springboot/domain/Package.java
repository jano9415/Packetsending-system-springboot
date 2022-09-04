package com.packetsending_system_springboot.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Package {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String uniquePackageId;
	
	//Kétoldali kapcsolat a Package és a User között.
	//Idegen kulcs a User tábla id attribútumára
	//Ez az osztály a birtokos, ez tartalmazza az idegen kulcsot.
	//Egy csomag csak egy regisztrált felhasználóhoz tartozhat.
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//Kétoldali kapcsolat a Package és a Container között
	//Idegen kulcs a Container tábla id attribútumára
	//Ez az osztály a birtokos, ez tartalmazza az idegen kulcsot.
	//Egy csomag objektum egy feladási container objektumot tartalmaz.
	@ManyToOne
	//@JoinColumn(name ="container_id")
	private Container shippingFrom;
	
	//Kétoldali kapcsolat a Package és a Container között
	//Idegen kulcs a Container tábla id attribútumára
	//Ez az osztály a birtokos, ez tartalmazza az idegen kulcsot.
	//Egy csomag objektum egy érkezési container objektumot tartalmaz.
	@ManyToOne
	//@JoinColumn(name = "container_id")
	private Container shippingTo;
	
	//Kétoldali kapcsolat a Package és a Box között
	//Idegen kulcs a Box tábla id attribútumára
	//Ez az osztály a birtokos, ez tartalmazza az idegen kulcsot.
	//Egy csomag objektum egy box objektumot tartalmaz. 
	@ManyToOne
	@JoinColumn(name = "box_id")
	private Box box;
	
	//packages_during_shipping kapcsolótábla
	//ez az osztály a birtokos
	@ManyToOne
	@JoinTable(
			name = "packages_during_shipping",
			joinColumns = {@JoinColumn(name = "package_id")},
			inverseJoinColumns = {@JoinColumn(name = "courier_id")})
	private Courier courier;
	
	//packages_in_container kapcsolótábla
	//ez az osztály a birtokos
	@ManyToMany(/*fetch= FetchType.EAGER*/)
	@JoinTable(
			name = "packages_in_container",
			joinColumns = {@JoinColumn(name = "package_id")},
			inverseJoinColumns = {@JoinColumn(name = "container_id")})
	private Set<Container> containers = new HashSet<Container>();
	
	/*@ManyToMany
	@JoinTable(
			name = "packages_in_container",
			joinColumns = {@JoinColumn(name = "box_id")},
			inverseJoinColumns = {@JoinColumn(name = "package_id")})
	private Set<Box> boxes = new HashSet<Box>();*/
	
	private int width;
	
	private int height;
	
	private int length;
	
	private int price;
	
	private String receiverFirstName;
	
	private String receiverLastName;
	
	private String receiverEmailAddress;
	
	private boolean packageIsShipped;
	
	private boolean packageIsTaken;
	
	private Date sendingDate;
	
	private Time sendingTime;
	
	private Date takingDate;
	
	private Time takingTime;
	
	private Date shippingDate;
	
	private Time shippingTime;
	
	public Package() {
		
	}



	public Package(String uniquePackageId, User userId, Container shippingFrom, Container shippingTo,
			int width, int height, int length, int price, String receiverFirstName, String receiverLastName,
			String receiverEmailAddress, boolean packageIsShipped, boolean packageIsTaken, Date sendingDate,
			Time sendingTime, Date takingDate, Time takingTime, Date shippingDate, Time shippingTime) {
		this.uniquePackageId = uniquePackageId;
		this.user = userId;
		this.shippingFrom = shippingFrom;
		this.shippingTo = shippingTo;
		this.width = width;
		this.height = height;
		this.length = length;
		this.price = price;
		this.receiverFirstName = receiverFirstName;
		this.receiverLastName = receiverLastName;
		this.receiverEmailAddress = receiverEmailAddress;
		this.packageIsShipped = packageIsShipped;
		this.packageIsTaken = packageIsTaken;
		this.sendingDate = sendingDate;
		this.sendingTime = sendingTime;
		this.takingDate = takingDate;
		this.takingTime = takingTime;
		this.shippingDate = shippingDate;
		this.shippingTime = shippingTime;
	}



	public Long getId() {
		return id;
	}

	public String getUniquePackageId() {
		return uniquePackageId;
	}

	public void setUniquePackageId(String uniquePackageId) {
		this.uniquePackageId = uniquePackageId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Container getShippingFrom() {
		return shippingFrom;
	}

	public void setShippingFrom(Container shippingFrom) {
		this.shippingFrom = shippingFrom;
	}

	public Container getShippingTo() {
		return shippingTo;
	}

	public void setShippingTo(Container shippingTo) {
		this.shippingTo = shippingTo;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	public String getReceiverFirstName() {
		return receiverFirstName;
	}



	public void setReceiverFirstName(String receiverFirstName) {
		this.receiverFirstName = receiverFirstName;
	}



	public String getReceiverLastName() {
		return receiverLastName;
	}



	public void setReceiverLastName(String receiverLastName) {
		this.receiverLastName = receiverLastName;
	}



	public String getReceiverEmailAddress() {
		return receiverEmailAddress;
	}



	public void setReceiverEmailAddress(String receiverEmailAddress) {
		this.receiverEmailAddress = receiverEmailAddress;
	}



	public boolean isPackageIsShipped() {
		return packageIsShipped;
	}

	public void setPackageIsShipped(boolean packageIsShipped) {
		this.packageIsShipped = packageIsShipped;
	}

	public boolean isPackageIsTaken() {
		return packageIsTaken;
	}

	public void setPackageIsTaken(boolean packageIsTaken) {
		this.packageIsTaken = packageIsTaken;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public Time getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(Time sendingTime) {
		this.sendingTime = sendingTime;
	}

	public Date getTakingDate() {
		return takingDate;
	}

	public void setTakingDate(Date takingDate) {
		this.takingDate = takingDate;
	}

	public Time getTakingTime() {
		return takingTime;
	}

	public void setTakingTime(Time takingTime) {
		this.takingTime = takingTime;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Time getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Time shippingTime) {
		this.shippingTime = shippingTime;
	}



	public Courier getCourier() {
		return courier;
	}



	public void setCourier(Courier courier) {
		this.courier = courier;
	}



	public Set<Container> getContainers() {
		return containers;
	}



	public void setContainers(Set<Container> containers) {
		this.containers = containers;
	}



	public Box getBox() {
		return box;
	}



	public void setBox(Box box) {
		this.box = box;
	}



	/*public Set<Box> getBoxes() {
		return boxes;
	}



	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
