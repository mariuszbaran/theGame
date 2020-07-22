package com.rationalram;

public class Route {
	private final String ROUTE = "route";
	private final String INTERCHANGE = "interchange";
	private int id = -1;
	private int points = -1;
	private String cityA = "-1";
	private String cityB = "-1";
	private String image = "-1";
	private int costColor = -1;
	private int costSpecial = -1;
	private String requiredColor = "-1";//required color to build
	private Player nobody = new Player();
	private String type = "-1";
	
	protected Player getNobody() {
		return nobody;
	}
	private Player ownedBy = nobody;//player
	
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
	public Player getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}
	public int getCostColor() {
		return costColor;
	}
	public void setCostColor(int costColor) {
		this.costColor = costColor;
	}
	public int getCostSpecial() {
		return costSpecial;
	}
	public void setCostSpecial(int costSpecial) {
		this.costSpecial = costSpecial;
	}
	public String getRequiredColor() {
		return requiredColor;
	}
	public void setRequiredColor(String requiredColor) {
		this.requiredColor = requiredColor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getROUTE() {
		return ROUTE;
	}
	public String getINTERCHANGE() {
		return INTERCHANGE;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
