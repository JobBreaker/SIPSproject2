package com.example.json;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.android.gms.maps.model.LatLng;

public class ParkingObject {
private long id;
private URI uri;
private String parking_name,attraction,privillege,detail,address;
private int capacity,car;
private LatLng latlng;
public ParkingObject(long id){
	this.id=id;
}
public ParkingObject(long id,String parking_name,String attraction,String privillege,int capacity,int car,LatLng latLng){
	this.id=id;
	this.attraction = attraction;
	this.parking_name = parking_name;
	this.privillege = privillege;
	this.capacity = capacity;
	this.car = car;
	this.latlng = latLng;
}

public void setId(long id){
	this.id = id;
}
public void setURI(String uri){
	try {
		this.uri = new URI(uri);
	} catch (URISyntaxException e) {
		this.uri = null;
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void setParkingDetail(String parking_name,String attraction,String privillege){
	this.parking_name = parking_name;
	this.attraction = attraction;
	this.privillege = privillege;
}
public void setCarCapacity(int capacity,int car){
	this.car = car;
	this.capacity = capacity;
}

public void setLatLng(int lat,int lng){
	this.latlng = new LatLng(lat,lng);
}
public void setLatLng(LatLng latlng){
	this.latlng = latlng;
}
public void setAddress(String address){
	this.address = address;
}
public void setDetail(String detail){
	this.detail = detail;
}
public long getId(){
	return this.id;
}
public String getParkingName(){
	return this.parking_name;
}
public String getAttraction(){
	return this.attraction;
}
public String getPrivillege(){
	return this.privillege;
}
public String getDetail(){
	return this.detail;
}
public URI getURI(){
	return this.uri;
}
public String getAddress(){
	return this.address;
}
public int getCapacity(){
	return this.capacity;
}
public int getCar(){
	return this.car;
}
public int getCarRemaining(){
	return this.capacity-this.car;
}
public LatLng getlatLng(){
	return this.latlng;
}
}
