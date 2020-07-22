package com.rationalram;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int id = -1;
	private String remoteAddr = "-1";
	private String name = "-1";
	private int gameId = -1;
	private String color = "-1";
	private String avatar = "-1";
	private int points = -1;
	private int pawnQty = -1;
	private int interchangeQty = - 1;
	private ColorDeck handDeck = new HandDeck();
	private BuildingDeck buildingDeck = new BuildingDeck();
	private TicketDeck shortTicketDeck = new TicketDeck();
	private TicketDeck longTicketDeck = new TicketDeck();
	private TicketDeck temporaryTicketDeck = new TicketDeck();
	private Routes routes = new Routes();
	private boolean active = false;
	private int turns = -1;
	private int actionPoints = -1;
	private String newCard = "-1";
	private ActionMode actionMode = ActionMode.none;
	
	/*
	 * Scan a list of routes and remove all connections that does not belong to the player.
	 * They are of type INTERCHANGE.
	 * Another player is owner of this connection.
	 */
	public List<Route> removeInterchangeConnections(List<Route> routeList) {
		int lastIndex = routeList.size()-1;
		for(int i = lastIndex; i >= 0; i--) {
			if(routeList.get(i).getType().equals(routeList.get(i).getINTERCHANGE())) {
				routeList.remove(i);
			}
		}
		return routeList;
	}
	
	public int getLongestConnection(Routes routes) {
		return getLongestConnection("",routes.getRoutesList());
	}
	
	/*
	 * return the longest connection, connections of type INTERCHANGE are not included. They are not counted
	 * in this function.
	 */
	public int getLongestConnection(String startLocation, List<Route> routeList) {
		int sum = 0;
		List<Route> list = removeInterchangeConnections(routeList); //removes connection of type Interchange and leaves only connection that were build by the player
		
		for(int i = 0; i < list.size();i++) {
			if(startLocation.contentEquals("")) {
				String routeCityA = list.get(i).getCityA();
				String routeCityB = list.get(i).getCityB();
				List<Route> tempList = new ArrayList<Route>(list);
				tempList.remove(i);
				int routeLength = list.get(i).getCostColor() + list.get(i).getCostSpecial();
				int tempSum1 = routeLength + getLongestConnection(routeCityB,tempList);
				int tempSum2 = routeLength + getLongestConnection(routeCityA,tempList);
				
				int tempSum = tempSum1 > tempSum2 ? tempSum1 : tempSum2;
				if(tempSum > sum) sum=tempSum;
				
			}else {
				String routeCityA = list.get(i).getCityA();
				String routeCityB = list.get(i).getCityB();
				List<Route> tempList = new ArrayList<Route>(list);
				tempList.remove(i);
				int routeLength = list.get(i).getCostColor() + list.get(i).getCostSpecial();
				int tempSum1 = 0;
				int tempSum2 = 0;
				if(startLocation.contentEquals(routeCityA)) {
					tempSum1 = routeLength + getLongestConnection(routeCityB,tempList);
				}
				if(startLocation.contentEquals(routeCityB)) {
					tempSum2 = routeLength + getLongestConnection(routeCityA, tempList);
				}
				
				int tempSum = tempSum1 > tempSum2 ? tempSum1 : tempSum2;
				if(tempSum > sum) sum=tempSum;
			}

		}
		
		
		return sum;
	}
	
	public boolean verifyConnection(Ticket ticket, Routes routes) {
		List<Route> list = routes.getRoutesList();
		String ticketCityA = ticket.getCityA();
		String ticketCityB = ticket.getCityB();
		for(int i =0; i < list.size();i++) {
			String routeCityA = list.get(i).getCityA();
			String routeCityB = list.get(i).getCityB();
			
			if(routeCityA.contentEquals(ticketCityA)) {
				if(routeCityB.contentEquals(ticketCityB)) {
					return true;
				}else {
					Routes tempRoutes = new Routes();
					tempRoutes.setRoutesList(new ArrayList<Route>(list));
					tempRoutes.getRoutesList().remove(i);
					Ticket tempTicket = new Ticket();
					tempTicket.setCityA(routeCityB);
					tempTicket.setCityB(ticketCityB);
					if(verifyConnection(tempTicket,tempRoutes)) return true;
				}
			}
			if(routeCityB.contentEquals(ticketCityA)) {
				if(routeCityA.contentEquals(ticketCityB)) {
					return true;
				}else {
					Routes tempRoutes = new Routes();
					tempRoutes.setRoutesList(new ArrayList<Route>(list));
					tempRoutes.getRoutesList().remove(i);
					Ticket tempTicket = new Ticket();
					tempTicket.setCityA(routeCityA);
					tempTicket.setCityB(ticketCityB);
					if(verifyConnection(tempTicket,tempRoutes)) return true;
				}
			}
		}
		return false;
	}
	
	public Player(String name) {
		this.setName(name);
	}
	
	public Player() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPawnQty() {
		return pawnQty;
	}

	public void setPawnQty(int pawnQty) {
		this.pawnQty = pawnQty;
	}

	public int getInterchangeQty() {
		return interchangeQty;
	}

	public void setInterchangeQty(int interchangeQty) {
		this.interchangeQty = interchangeQty;
	}

	public ColorDeck getHandDeck() {
		return handDeck;
	}

	public void setHandDeck(ColorDeck handDeck) {
		this.handDeck = handDeck;
	}

	public TicketDeck getShortTicketDeck() {
		return shortTicketDeck;
	}

	public void setShortTicketDeck(TicketDeck shortTicketDeck) {
		this.shortTicketDeck = shortTicketDeck;
	}

	public TicketDeck getLongTicketDeck() {
		return longTicketDeck;
	}

	public void setLongTicketDeck(TicketDeck longTicketDeck) {
		this.longTicketDeck = longTicketDeck;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public TicketDeck getTemporaryTicketDeck() {
		return temporaryTicketDeck;
	}

	public void setTemporaryTicketDeck(TicketDeck temporaryTicketDeck) {
		this.temporaryTicketDeck = temporaryTicketDeck;
	}

	public Routes getRoutes() {
		return routes;
	}

	public void setRoutes(Routes routes) {
		this.routes = routes;
	}
	public void increasePoints(int points) {
		this.points += points;
	}
	public void decreaseActionPointsBy(int actionCost) {
		this.actionPoints -= actionCost;
	}
	public void decreasePawns(int number) {
		this.pawnQty -= number;
	}
	public void decreaseInterchangeQtyBy(int number) {
		this.interchangeQty -= number;
	}
	public void decreaseTurns() {
		this.turns--;
	}
	public ActionMode getActionMode() {
		return actionMode;
	}

	public void setActionMode(ActionMode actionMode) {
		this.actionMode = actionMode;
	}

	public BuildingDeck getBuildingDeck() {
		return buildingDeck;
	}

	public void setBuildingDeck(ColorDeck buildingDeck) {
		this.buildingDeck = (BuildingDeck) buildingDeck;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public String getNewCard() {
		return newCard;
	}

	public void setNewCard(String newCard) {
		this.newCard = newCard;
	}
	

}
