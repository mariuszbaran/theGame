//filtered data, game info and player info
package com.rationalram;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class GameState {
	//general state for everyone
	private String gameId = "-1";
	private String alert = "-1";
	private String tableDeckTotal = "-1";
	private String shortTicketTotal = "-1";
	private String usedDeckTotal = "-1";
	private List<List<String>> routes;
	
	//open card 0 - 4
	private List<String> openCardList;
	
	//player 0 - 5
	//name, pints, color, remoteAddr, active, cardsQty.
	private List<List<String>> playerList;
	
	//player
	private String name = "-1";
	private String points = "-1";
	private String color = "-1";
	private String active = "-1";
	private String redCards = "-1";
	private String pinkCards = "-1";
	private String blueCards = "-1";
	private String blackCards = "-1";
	private String greenCards = "-1";
	private String orangeCards = "-1";
	private String yellowCards = "-1";
	private String whiteCards = "-1";
	private String specialCards = "-1";
	private String interchangeCards = "-1";
	private List<String> tempTicketsList;
	private List<String> shortTicketsList;
	private String bdColor = "-1";
	private String bdSpecial = "-1";
	private String bdColorBg = "-1";
	private String bdSpecialBg = "-1";
	private String acceptActive = "-1";
	private String actionMode = "-1";
	private String newCard = "-1";
	
	
	public GameState(Game game, Player player) {
		//general state for everyone
		this.setGameId(Integer.toString(game.getId()));
		this.setAlert("No alert");
		this.setTableDeckTotal(Integer.toString(game.getTableDeck().getCardTotalQty()));
		this.setUsedDeckTotal(Integer.toString(game.getUsedDeck().getCardTotalQty()));
		
		//set open deck
		List<String> list = new ArrayList<String>();
		for(Card card: game.getOpenDeck().getCardList()) {
			list.add(card.getColor());
		}
		this.setOpenCardList(list);
		
		//player list with attributes
		playerList = new ArrayList<>();
		for(Player p : game.getPlayerList()) {
			List<String> playerAttributes = new ArrayList<>();
			/*
			 name
			 points
			 hand deck cards qty
			 pawns
			 interchange
			 active
			 color
			 */
			playerAttributes.add(p.getName());
			playerAttributes.add(Integer.toString(p.getPoints()));
			playerAttributes.add(Integer.toString(p.getHandDeck().getCardTotalQty()+p.getBuildingDeck().getCardTotalQty()));
			playerAttributes.add(Integer.toString(p.getPawnQty()));
			playerAttributes.add(Integer.toString(p.getInterchangeQty()));
			playerAttributes.add(Boolean.toString(p.isActive()));
			playerAttributes.add(p.getColor());
			playerList.add(playerAttributes);
		}
		
		//routes
		routes = new ArrayList<>();
		for(Route r: game.getRoutes().getRoutesList()) {
			List<String> parameter = new ArrayList<String>();
			parameter.add(Integer.toString(r.getId()));
			parameter.add(r.getOwnedBy().getColor());
			routes.add(parameter);
		}
		
		//player
		setName(player.getName());
		setPoints(Integer.toString(player.getPoints()));
		setColor(player.getColor());
		setActive(Boolean.toString(player.isActive()));
		setBlackCards(Integer.toString(player.getHandDeck().getBlackCardQty()));
		setRedCards(Integer.toString(player.getHandDeck().getRedCardQty()));
		setBlueCards(Integer.toString(player.getHandDeck().getBlueCardQty()));
		setGreenCards(Integer.toString(player.getHandDeck().getGreenCardQty()));
		setOrangeCards(Integer.toString(player.getHandDeck().getOrangeCardQty()));
		setYellowCards(Integer.toString(player.getHandDeck().getYellowCardQty()));
		setPinkCards(Integer.toString(player.getHandDeck().getPinkCardQty()));
		setWhiteCards(Integer.toString(player.getHandDeck().getWhiteCardQty()));
		setSpecialCards(Integer.toString(player.getHandDeck().getSpecialCardQty()));
		setInterchangeCards(Integer.toString(player.getInterchangeQty()));
		setActionMode(player.getActionMode().toString());
		setNewCard(player.getNewCard());
		
		if(!player.getBuildingDeck().isEmpty()) {
			bdColorBg = player.getBuildingDeck().getColor();
			bdSpecialBg = player.getBuildingDeck().getColorSpecial();
			bdColor = Integer.toString(player.getBuildingDeck().getQtyByColor(bdColorBg));
			bdSpecial = Integer.toString(player.getBuildingDeck().getSpecialCardQty());
		}else {
			bdColor = "0";
			bdSpecial = "0";
			bdColorBg = "empty";
			bdColorBg = "empty";
		}
		
		this.tempTicketsList = new ArrayList<String>();
		for(Ticket t:player.getTemporaryTicketDeck().getTicketList()) {
			this.tempTicketsList.add("singleTicket"+Integer.toString(t.getId()));
		}
		if(!(this.tempTicketsList.isEmpty()) && this.tempTicketsList.size() < 3) {
			setAcceptActive("true");
		}else {
			setAcceptActive("false");
		}
		this.shortTicketsList = new ArrayList<String>();
		for(Ticket t1:player.getShortTicketDeck().getTicketList()) {
			this.shortTicketsList.add("singleTicket"+Integer.toString(t1.getId()));
		}
		setShortTicketTotal(Integer.toString(game.getShortTicketDeck().getTicketsTotalQty()));
	
	}
	
	public GameState(Game game) {
		//general state without player info
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Field[] fields = GameState.class.getDeclaredFields();
		for(int i = 0; i<fields.length;i++) {		
			try {
				json.put(fields[i].getName(),fields[i].get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
		
		return json;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getTableDeckTotal() {
		return tableDeckTotal;
	}

	public void setTableDeckTotal(String tableDeckTotal) {
		this.tableDeckTotal = tableDeckTotal;
	}

	public List<String> getOpenCardList() {
		return openCardList;
	}

	public void setOpenCardList(List<String> openCardList) {
		this.openCardList = openCardList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRedCards() {
		return redCards;
	}

	public void setRedCards(String redCards) {
		this.redCards = redCards;
	}

	public String getPinkCards() {
		return pinkCards;
	}

	public void setPinkCards(String pinkCards) {
		this.pinkCards = pinkCards;
	}

	public String getBlueCards() {
		return blueCards;
	}

	public void setBlueCards(String blueCards) {
		this.blueCards = blueCards;
	}

	public String getBlackCards() {
		return blackCards;
	}

	public void setBlackCards(String blackCards) {
		this.blackCards = blackCards;
	}

	public String getGreenCards() {
		return greenCards;
	}

	public void setGreenCards(String greenCards) {
		this.greenCards = greenCards;
	}

	public String getOrangeCards() {
		return orangeCards;
	}

	public void setOrangeCards(String orangeCards) {
		this.orangeCards = orangeCards;
	}

	public String getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(String yellowCards) {
		this.yellowCards = yellowCards;
	}

	public String getWhiteCards() {
		return whiteCards;
	}

	public void setWhiteCards(String whiteCards) {
		this.whiteCards = whiteCards;
	}

	public String getSpecialCards() {
		return specialCards;
	}

	public void setSpecialCards(String specialCards) {
		this.specialCards = specialCards;
	}

	public String getInterchangeCards() {
		return interchangeCards;
	}

	public void setInterchangeCards(String interchangeCards) {
		this.interchangeCards = interchangeCards;
	}

	public List<String> getTempTicketsList() {
		return tempTicketsList;
	}

	public void setTempTicketsList(List<String> tempTicketsList) {
		this.tempTicketsList = tempTicketsList;
	}

	public List<String> getShortTicketsList() {
		return shortTicketsList;
	}

	public void setShortTicketsList(List<String> shortTicketsList) {
		this.shortTicketsList = shortTicketsList;
	}

	public String getAcceptActive() {
		return acceptActive;
	}

	public void setAcceptActive(String acceptActive) {
		this.acceptActive = acceptActive;
	}

	public String getShortTicketTotal() {
		return shortTicketTotal;
	}

	public void setShortTicketTotal(String shortTicketTotal) {
		this.shortTicketTotal = shortTicketTotal;
	}

	public List<List<String>> getRoutes() {
		return routes;
	}

	public void setRoutes(List<List<String>> routes) {
		this.routes = routes;
	}

	public String getBdColor() {
		return bdColor;
	}

	public void setBdColor(String bdColor) {
		this.bdColor = bdColor;
	}

	public String getBdSpecial() {
		return bdSpecial;
	}

	public void setBdSpecial(String bdSpecial) {
		this.bdSpecial = bdSpecial;
	}

	public String getBdColorBg() {
		return bdColorBg;
	}

	public void setBdColorBg(String bdColorBg) {
		this.bdColorBg = bdColorBg;
	}

	public String getBdSpecialBg() {
		return bdSpecialBg;
	}

	public void setBdSpecialBg(String bdSpecialBg) {
		this.bdSpecialBg = bdSpecialBg;
	}

	public String getUsedDeckTotal() {
		return usedDeckTotal;
	}

	public void setUsedDeckTotal(String usedDeckTotal) {
		this.usedDeckTotal = usedDeckTotal;
	}

	public String getActionMode() {
		return actionMode;
	}

	public void setActionMode(String actionMode) {
		this.actionMode = actionMode;
	}

	public String getNewCard() {
		return newCard;
	}

	public void setNewCard(String newCard) {
		this.newCard = newCard;
	}
}
