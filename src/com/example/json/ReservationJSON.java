package com.example.json;

import org.json.JSONException;
import org.json.JSONObject;

public class ReservationJSON {
	private long RESERVATION_REQUEST = 1000;
	private long parkingZone = 100;
	private String userId,privillegeId,userAgent,datetime;
	public ReservationJSON() {
		this.userAgent = "android";
		// TODO Auto-generated constructor stub
	}
	public ReservationJSON(String userId,String privillegeId,long parking,String datetiem){
		this.userId = userId;
		this.privillegeId = privillegeId;
		this.userAgent = "android";
		this.parkingZone = parking;
		this.datetime = datetiem;
		
	}
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", RESERVATION_REQUEST);
		json.put("userId", this.userId);
		json.put("privillege", this.privillegeId);
		json.put("agent", this.userAgent);
		json.put("parking", this.parkingZone);
		json.put("time", this.datetime);
		return json;
	}
	
}
