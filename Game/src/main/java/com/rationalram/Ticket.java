package com.rationalram;

public class Ticket {
	private int id = -1;
	private int points = -1;
	private String cityA = "-1";
	private String cityB = "-1";
	private String image = "-1";
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getCityA() {
		return cityA;
	}
	public void setCityA(String cityA) {
		this.cityA = cityA;
	}
	public String getCityB() {
		return cityB;
	}
	public void setCityB(String cityB) {
		this.cityB = cityB;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
