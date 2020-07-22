package com.rationalram;

import java.util.ArrayList;
import java.util.List;


public class Game {
	private int id = -1;
	private Player hostPlayer = null;
	private int minPlayers = -1;
	private int maxPlayers = -1;
	private List<Player> playerList = new ArrayList<Player>();
	private Chat chat = new Chat();
	private TableDeck tableDeck = new TableDeck();
	private OpenDeck openDeck = new OpenDeck();
	private TableDeck usedDeck = new TableDeck();
	private TicketDeck shortTicketDeck = new TicketDeck();
	private TicketDeck longTicketDeck = new TicketDeck();
	//private HashMap<String,Route> routeList = new HashMap<String,Route>();
	private Routes routes = new Routes();
	private boolean lastTurn = false;
	
	public Routes getRoutes() {
		return routes;
	}

	public void setRoutes(Routes routes) {
		this.routes = routes;
	}

	public Game() {
	}
	
	public Game(Player hostPlayer) {
		this.setHostPlayer(hostPlayer);
	}
	
	public Game(int id) {
		this.setId(id);
	}
	
	public Game(String id) {
		this.setId(Integer.parseInt(id));
	}
	
	//return game state with private info on player
	public GameState getGameState(Player player) {
		GameState gs = new GameState(this,player);
			return gs;
	}
	
	//return official state for spectator
	public GameState getGameState() {
		GameState gs = new GameState(this);
		return gs;
	}
	
	public void startGame(Player player) {
		if(this.hostPlayer.equals(player)) {
			
			//initialise table deck
			this.tableDeck.initialize();
			this.tableDeck.shuffle();
			this.tableDeck.shuffle();
			
			//init open deck
			this.openDeck.setMaxCardQty(5);
			this.openDeck.setMaxSpecialCardQty(3);
			for(int i = 0; i < this.openDeck.getMaxCardQty();i++) {
				Card card = this.tableDeck.pick();
				this.openDeck.put(card);
			}
			
			//init long tickets
			this.longTicketDeck.initLong();
			
			//init short tickets
			this.shortTicketDeck.initShort();
			
			//init routes
			this.routes.init();
			
			//init players
			for(Player p : this.playerList) {
				int startingHand = 6;
				p.setPawnQty(12);
				p.setInterchangeQty(3);
				for(int i = 0; i < startingHand; i++) {
					p.getHandDeck().put(this.getTableDeck().pick());
				}
				p.setPoints(0);
				p.setActionPoints(12);
				p.setActive(false);
				p.getBuildingDeck().setColor(p.getBuildingDeck().getColorEmpty());
				p.setTurns(999);
			}
			this.playerList.get(0).setActive(true);
			
		}else{
			System.out.println("Only host can start the game");}		
	}
	
	private void pickOpenDeck(int index, Player player) {
		Card card = this.getOpenDeck().getCardList().get(index);
		int actionCost = card.getActionCost();
		String color = card.getColor();
		int actionPoints = player.getActionPoints();
		if(actionPoints >= actionCost) {
			if(color != "empty") {
				Card c = this.getOpenDeck().pick(index);
				player.setNewCard(c.getColor());
				player.getHandDeck().put(c);
				player.decreaseActionPointsBy(actionCost);
			}else {
				System.out.println("There is no card here dummy");
			}		
		}else {
			System.out.println("You cannot pick this card");
		}
	}
	
	
	private void pickTableDeck(Player player) {
		int actionCost = 6;
		int actionPoints = player.getActionPoints();
		if(actionPoints >= actionCost) {
			player.getHandDeck().put(this.getTableDeck().pick());
			player.decreaseActionPointsBy(actionCost);
		}else {
			System.out.println("You cannot pick this card");
		}
	}

	
	//adds tickets to the map, must choose at least one.
	private void pickTicketDeck(Player player) {
		int actionCost = 12;
		int actionPoints = player.getActionPoints();
		if(actionPoints >= actionCost) {
			if(!this.shortTicketDeck.isEmpty()) {
				if(player.getTemporaryTicketDeck().isEmpty()) {
					player.setActionMode(ActionMode.choosingTicket);
					int ticketsQtyInDeck = this.shortTicketDeck.getTicketsTotalQty();
					int numberOfTicketsToPick = 3;
					if(ticketsQtyInDeck < 3 ) {numberOfTicketsToPick = ticketsQtyInDeck;}
					for(int i = 0; i < numberOfTicketsToPick ; i++) {
						player.getTemporaryTicketDeck().put(this.getShortTicketDeck().pick());
					}
				}else {
					System.out.println("Choose at least one ticket.");
				}
			}else {
				System.out.println("No more tickets availiable");
			}
		}else {
			System.out.println("You cannot pick this card");
		}
	}
	
	
	//builds route or interchange if there is opponent route already built
	private void buildRoute(int routeId, Player player) {
		this.routes.build(routeId, player, this);
	}
	
	
	private void chooseTicket(int ticketId, Player player) {
		if(player.getTemporaryTicketDeck().contains(ticketId)) {
			Ticket ticket = player.getTemporaryTicketDeck().pick(ticketId);
			player.getShortTicketDeck().put(ticket);
			if(player.getTemporaryTicketDeck().isEmpty()) {
				player.setActionPoints(0);
				player.setActionMode(ActionMode.none);
			}
		}else {
			System.out.println("Not available to pick");
		}
	}
	
	
	private void acceptTickets(Player player) {
		int size = player.getTemporaryTicketDeck().getTicketsTotalQty();
		if(size < 3) {
			/* puts the unwanted ticktets back to the ticket deck
			for(int i = 0 ; i < size ; i++) {
				Ticket ticket = player.getTemporaryTicketDeck().pick();
				this.getShortTicketDeck().put(ticket);
				this.getShortTicketDeck().shuffle();
			}
			*/
			//doesn't put tickets back to the deck
			player.getTemporaryTicketDeck().clear();
			player.setActionPoints(0);
			player.setActionMode(ActionMode.none);
		}else {
			System.out.println("You must choose at least one of three tickets");
		}
	}
	
	
	public boolean isEndTurn(Player player) {
		if(player.getActionPoints()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void endTurn(Player player) {
		int activePlayerIndex = this.getPlayerList().indexOf(player);
		player.setActive(false);
		if(activePlayerIndex == this.getPlayerList().size()-1) {
			this.getPlayerList().get(0).setActive(true);
		}else {
			this.getPlayerList().get(++activePlayerIndex).setActive(true);
		}
		player.setActionPoints(12);
		player.decreaseTurns();
		//top up table deck
		if(this.getTableDeck().getCardTotalQty() < 3) {
			this.getTableDeck().topupFrom(this.getUsedDeck());
		}
		//top up open deck
		this.openDeck.topup(this.tableDeck);
	}
	
	
	public boolean checkIfLastTurn(Player player) {
		return player.getPawnQty() <= 3 ? true : false;
	}
	
	
	public void initLastTurn() {
		for(Player player : this.getPlayerList()) {
			player.setTurns(1);
		}
		this.setLastTurn(true);
	}
	
	
	public boolean isEndGame() {
		boolean result = true;
		for(Player player : this.getPlayerList()) {
			if(player.getTurns() >= 1) result = false;
		}
		return result;
	}
	
	
	public void countPoints() {
		for(Player player : this.getPlayerList()) {
			int points = player.getPoints();
			
			if(!player.getShortTicketDeck().isEmpty()) {
				for(Ticket ticket : player.getShortTicketDeck().getTicketList()) {
					if(!player.getRoutes().getRoutesList().isEmpty()) {
						if(player.verifyConnection(ticket, player.getRoutes())) {
							points += ticket.getPoints();
						}else {
							points -= ticket.getPoints();
						}
					}else {
						points -= ticket.getPoints();
					}					
				}
			}
			
			if(!player.getLongTicketDeck().isEmpty()) {
				for(Ticket ticket : player.getLongTicketDeck().getTicketList()) {
					if(!player.getRoutes().getRoutesList().isEmpty()) {
						if(player.verifyConnection(ticket, player.getRoutes())) {
							points += ticket.getPoints();
						}else {
							points -= ticket.getPoints();
						}
					}else {
						points -= ticket.getPoints();
					}					
				}
			}
			
			switch(player.getInterchangeQty()) {
			case 3:
				points += 12;
				break;
			case 2:
				points += 8;
				break;
			case 1:
				points += 4;
				break;
			}
			
			player.setPoints(points);
		}
		
		int longestConnection = 0;
		
		for(Player player : this.getPlayerList()) {
			int longest = player.getLongestConnection(player.getRoutes());
			if(longest > longestConnection) {
				longestConnection = longest;
			}
		}
		 
		for(Player player : this.getPlayerList()) {
				if(longestConnection == player.getLongestConnection(player.getRoutes())) {
					player.increasePoints(10);
			}
		}
	}
	
	public void deactivePlayers() {
		for(Player player : this.getPlayerList()) {
			player.setActive(false);
		}
	}
	
	private void pickCardsToBuild(String color, Player player) {
		String c = color.toLowerCase();
		if(player.getHandDeck().getQtyByColor(c) != 0) {
			if(player.getBuildingDeck().getColor().equals(c)) {
				Card card = player.getHandDeck().pickByColor(c);
				player.getBuildingDeck().put(card);
			}else if(player.getBuildingDeck().getColorSpecial().equals(c)) {
				Card card = player.getHandDeck().pickByColor(c);
				player.getBuildingDeck().put(card);
			}else if(player.getBuildingDeck().getColor().equals("empty")) {
				Card card = player.getHandDeck().pickByColor(c);
				player.getBuildingDeck().put(card);
				player.getBuildingDeck().setColor(c);
			}else {
				System.out.println("You cannot add this card. Pick color: " + player.getBuildingDeck().getColor());
			}			
		}else {
			System.out.println("Not enought cards");
		}
	}
	
	private String getActionId(String actionName, String commonPart) {
		String s = actionName.replace(commonPart,"");
		return s;
	}
	
	public void action(Player player, String action) {
		String openCard = "openCard";
		String tableDeck = "tableDeck";
		String ticketDeck = "ticketDeck";
		String route = "route";
		String connection = "conn";
		String ticket = "singleTicket";
		String accept = "accept";
		String cancel = "cancel";
		String handCard = "handDeck";
		String newConnection = "newConnection";
		String confirmation = "conf-";
		ActionMode actionMode = player.getActionMode();	

		// action for open card pressed - draw a card from open card deck
		if(action.contains(openCard)) {
			if(actionMode.equals(ActionMode.none)) {
				if(player.getActionPoints() >= 6) {
					String s = getActionId(action,openCard);
					//temporary to solve lack of proper GUI
					if(s.contains("Special")) {
						s = getActionId(s,"Special");
					}
					int id = Integer.parseInt(s);
					pickOpenDeck(id,player);
				}
			}
		}
		
		//action for table deck pressed - draw a card from deck
		if(action.contains(tableDeck)) {
			if(!this.getTableDeck().isEmpty()) {
				if(actionMode.equals(ActionMode.none)) {
					if(player.getActionPoints() >= 6) {
						pickTableDeck(player);
					}
				}	
			}else {
				System.out.println("No more cards to draw. Use cards from your hands, morons");
			}		
		}
		
		//action for ticket deck pressed - draw tickets from ticket deck
		if(action.contains(ticketDeck)) {
			if(actionMode.equals(ActionMode.none)) {
				if(player.getActionPoints() == 12) {
					pickTicketDeck(player);
				}
			}
		}
		
		//action for Single ticket picked		
		if(action.contains(ticket)) {
			if(actionMode.equals(ActionMode.choosingTicket)) {
				String s = getActionId(action,ticket);
				int id = Integer.parseInt(s);			
				chooseTicket(id,player);
			}
		}
		
		//
		if(action.contains(accept)) {
			if(actionMode.equals(ActionMode.choosingTicket)) {
				acceptTickets(player);
				player.setActionMode(ActionMode.none);
			}
		}
		
		//action for route pressed - go to building connection mode
		if(action.contains(newConnection)) {
			if(actionMode.equals(ActionMode.none)) {
				if(player.getActionPoints() == 12) {
					player.setActionMode(ActionMode.buildingConn);
					System.out.println("Pick Cards to build route");
				}
			}
		}
		
		if(action.contains(confirmation)) {
			String s = getActionId(action,confirmation);
			if(s.contentEquals(cancel)) {
				if(actionMode.equals(ActionMode.buildingConn)) {
					if(!player.getBuildingDeck().isEmpty()) {
						int cardQty = player.getBuildingDeck().getCardTotalQty();
						for(int i = 0; i < cardQty; i++) {
							Card card = player.getBuildingDeck().pick();
							player.getHandDeck().put(card);
						}
					}
					player.setActionMode(ActionMode.none);
					player.getBuildingDeck().setColor(player.getBuildingDeck().getColorEmpty());
				}
			}
		}
		
		//pick cards for building a connection
		if(action.contains(handCard)) {			
			if(actionMode.equals(ActionMode.buildingConn)) {				
				String color = getActionId(action, handCard);
				pickCardsToBuild(color,player);
			}	
		}
		
		//action for route pressed - build a connection
		if(action.contains(route)) {
			if(actionMode.equals(ActionMode.buildingConn)) {
					String s = getActionId(action,route);
					int id = Integer.parseInt(s);					
					buildRoute(id,player);
			}
		}
		
		//action for conn pressed - go to building mode
		if(action.contains(connection)) {
			if(actionMode.equals(ActionMode.none)) {
				if(player.getActionPoints() == 12) {
					player.setActionMode(ActionMode.buildingConn);
					System.out.println("Pick Cards to build interchange/route");
				}
			}
		}
		
		//action for conn pressed - build interchange
		if(action.contains(connection)) {
			if(actionMode.equals(ActionMode.buildingConn)) {
				String s = getActionId(action,connection);
				int id = Integer.parseInt(s);
				buildRoute(id,player);
			}
		}
	}
	
	public void addPlayer(Player player) {
		if(!this.playerList.contains(player)) {
			this.playerList.add(player);
		}else {System.out.println("Game: Player already exists");}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public Player getHostPlayer() {
		return hostPlayer;
	}

	public void setHostPlayer(Player hostPlayer) {
		this.hostPlayer = hostPlayer;
		this.addPlayer(hostPlayer);
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public TableDeck getTableDeck() {
		return tableDeck;
	}

	public void setTableDeck(TableDeck tableDeck) {
		this.tableDeck = tableDeck;
	}

	public OpenDeck getOpenDeck() {
		return openDeck;
	}

	public void setOpenDeck(OpenDeck openDeck) {
		this.openDeck = openDeck;
	}


	public TableDeck getUsedDeck() {
		return usedDeck;
	}

	public void setUsedDeck(TableDeck usedDeck) {
		this.usedDeck = usedDeck;
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

	public boolean isLastTurn() {
		return lastTurn;
	}

	public void setLastTurn(boolean lastTurn) {
		this.lastTurn = lastTurn;
	}

}
