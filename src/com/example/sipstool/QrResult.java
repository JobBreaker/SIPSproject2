package com.example.sipstool;

public class QrResult {
	private int floor,mall;
	private String zone;
	public QrResult(String result){
		String [] parts = result.split(",");
		if (parts.length==3){
			try{
			this.mall = Integer.parseInt(parts[0].split("=")[1]);
			this.floor = Integer.parseInt(parts[1].split("=")[1]);
			this.zone = parts[2].split("=")[1];
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public int getFloor(){
		return this.floor;
	}
	public int getMall(){
		return this.mall;
	}
	public String getZone(){
		return this.zone;
	}
}
