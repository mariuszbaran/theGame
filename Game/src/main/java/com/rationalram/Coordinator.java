//Singleton class to manage all the rest. The king, one class to rule the other classes.
package com.rationalram;

import java.util.HashMap;

public class Coordinator {
	private static Coordinator coordinator = null;
	
	private Coordinator() {
	}
	
	public static Coordinator getInstance() {
		if(coordinator == null) {
			coordinator = new Coordinator();
		}
		return coordinator;
	}
	
	//game Id string, and game
	private HashMap<String,Game> gameList = new HashMap<String,Game>();
	//player address IP and Player
	private HashMap<String,Player> playerList = new HashMap<String,Player>();
	private int gameId = 100001;
	
	//loads games list from the database
	public void loadGameList() {
		Game game = new Game();
		game.setId(this.gameId);
		Player player = this.playerList.get("0:0:0:0:0:0:0:1");
		player.setGameId(this.gameId);
		game.setHostPlayer(player);
		this.gameList.put(Integer.toString(this.gameId),game);
		//game.addPlayer(this.playerList.get("192.168.0.15"));
		game.addPlayer(this.playerList.get("192.168.0.15"));
		//game.addPlayer(this.playerList.get("192.168.0.15"));
		//game.addPlayer(this.playerList.get("0:0:0:0:0:0:0:1"));
		game.startGame(player);
	}
	//loads players from the database
	public void loadPlayerList() {
		//for test purposes create users here
		Player player1 = new Player();
		player1.setGameId(this.gameId);
		player1.setRemoteAddr("0:0:0:0:0:0:0:1");
		player1.setName("MariuszDell");
		player1.setColor("red");
		this.playerList.put("0:0:0:0:0:0:0:1",player1);
		
		Player player2 = new Player();
		player2.setGameId(this.gameId);
		player2.setRemoteAddr("192.168.0.15");
		player2.setName("MariuszSamsung");
		player2.setColor("blue");
		this.playerList.put(player2.getRemoteAddr(),player2);
		
	}
	//creates and adds game to the list, 
	public void createGame(Player host) {	
	}
	//creates game, adds it to the list and needs fields to be added later
	public void createGame() {
		
	}
	//removes game from the list if it exists and if the client is the host of this game.
	public void removeGame(String gameId, Player host) {	
	}
	public void joinGame(Player player) {	
	}
	public void leaveGame(Player player) {
		
	}
	public void startGame() {
		//set cards, shuffle decks, set players, colours, give cards to players etc.
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public HashMap<String,Game> getGameList() {
		return gameList;
	}

	public void setGameList(HashMap<String,Game> gameList) {
		this.gameList = gameList;
	}

	public HashMap<String,Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(HashMap<String,Player> playerList) {
		this.playerList = playerList;
	}

}
