package com.example.json;

import java.net.URI;

import com.google.android.gms.maps.model.LatLng;

public class ParkingDetailObject {
	long id;
	private String name,attractions,privillege,address,contact,shopping,dining;
	private int logo;LatLng latlng;
	private int capacity;
	public ParkingDetailObject(long id){
		this.id = id;
	}
	public ParkingDetailObject(long id,String name,String attractions,String privillege,String address,
			String contact,String shopping,String dining,int logo,LatLng latlng,int capacity){
	this.id = id;
	this.name = name;
	this.attractions = attractions;
	this.privillege = privillege;
	this.address = address;
	this.contact = contact;
	this.shopping = shopping;
	this.dining = dining;
	this.logo = logo;
	this.latlng = latlng;
	this.capacity = capacity;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setId(long id){
		this.id = id;
		// this is sample case for demo
	}
	public void setAttractions(String attraction){
		this.attractions = attraction;
	}
	public void setPrivillege(String privillege){
		this.privillege = privillege;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setContact(String contact){
		this.contact = contact;
	}
	public void setShopping(String shopping){
		this.shopping = shopping;
	}
	public void setDining(String dining){
		this.dining = dining;
	}
	public void setURI(int logo){
		this.logo = logo;
	}
	public void setLatLng(LatLng latlng){
		this.latlng = latlng;
	}
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getAttractions(){
		return this.attractions;
	}
	public String getPrivillege(){
		return this.privillege;
	}
	public String getAddress(){
		return this.address;
	}
	public String getShopping(){
		return this.shopping;
	}
	public String getContact(){
		return this.contact;
	}
	public String getDining(){
		return this.dining;
	}
	public int getLogotype(){
		return this.logo;
	}

	public LatLng getLatLng(){
		return this.latlng;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public boolean haveValetParking(){
		if (this.privillege.contains("valet")){
			return true;
		}
		return false;
	}
	public boolean haveReservation(){
		if (this.privillege.contains("reservation")){
			return true;
		}
		return false;
	}
		
		
	}
