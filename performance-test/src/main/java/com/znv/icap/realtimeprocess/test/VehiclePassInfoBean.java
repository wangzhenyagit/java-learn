package com.znv.icap.realtimeprocess.test;

import com.alibaba.fastjson.JSONObject;

public class VehiclePassInfoBean {
	private String eventId;
	private String plate;
	private int eventType;
	private int plateCategory;
	private int plateColor;
	private int vehicleCategory;
	private int vehicleType;
	private int vehicleLength;
	private int vehicleColor;
	private int speed;
	private String landPicture;
	private String platePicture;
	private String landId;
	private String passTime;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public int getPlateCategory() {
		return plateCategory;
	}
	public void setPlateCategory(int plateCategory) {
		this.plateCategory = plateCategory;
	}
	public int getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}
	public int getVehicleCategory() {
		return vehicleCategory;
	}
	public void setVehicleCategory(int vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}
	public int getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(int vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public int getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(int vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getLandPicture() {
		return landPicture;
	}
	public void setLandPicture(String landPicture) {
		this.landPicture = landPicture;
	}
	public String getLandId() {
		return landId;
	}
	public void setLandId(String landId) {
		this.landId = landId;
	}
	public String getPassTime() {
		return passTime;
	}
	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("{\"index\":{\"_id\":\"");
		ret.append(this.getEventId());
		ret.append("\"}}\n");
		
		JSONObject tmp = new JSONObject();
		tmp.put("event_id", this.getEventId());
		tmp.put("event_type", this.getEventType());
		tmp.put("land_id", this.getLandId());
		tmp.put("land_picture", this.getLandPicture());
		tmp.put("plate_picture", this.getPlatePicture());
		tmp.put("plate", this.getPlate());
		tmp.put("plate_color", this.getPlateColor());
		tmp.put("plate_category", this.getPlateCategory());
		tmp.put("pass_time", this.getPassTime());
		tmp.put("plate_color", this.getPlateColor());
		tmp.put("vehicle_color", this.getVehicleColor());
		tmp.put("vehicle_length", this.getVehicleLength());
		tmp.put("vehicle_category", this.getVehicleCategory());
		tmp.put("speed", this.getSpeed());

		ret.append(tmp.toString());
		return ret.toString();
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getPlatePicture() {
		return platePicture;
	}
	public void setPlatePicture(String platePicture) {
		this.platePicture = platePicture;
	}
}

