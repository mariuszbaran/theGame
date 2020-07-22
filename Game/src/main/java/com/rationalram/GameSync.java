package com.rationalram;

import java.io.IOException;	
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameSync extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Coordinator coordinator = Coordinator.getInstance();

    public GameSync() {
    	
    	// remove later after adding login in functionality/////////////////////////////////////////////////
    	coordinator.loadPlayerList();
    	coordinator.loadGameList();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Player player = coordinator.getPlayerList().get(request.getRemoteAddr());
		String gameId = Integer.toString(player.getGameId());
		Game game = coordinator.getGameList().get(gameId);
		GameState gameState = game.getGameState(player);
		response.getWriter().print(gameState.toJSON());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Player player = coordinator.getPlayerList().get(request.getRemoteAddr());
		String gameId = Integer.toString(player.getGameId());
		Game game = coordinator.getGameList().get(gameId);
		String actionId = request.getParameter("id");
		System.out.println(actionId);
		if(player.isActive()) {
			game.action(player,actionId);
			if(game.isEndTurn(player)) game.endTurn(player);
			if(!game.isLastTurn()) {
				if(game.checkIfLastTurn(player)) game.initLastTurn();
			}		
			if(game.isEndGame()) {
				game.deactivePlayers();
				game.countPoints();
			}
		}else {response.getWriter().print("Not your turn");}
	}
}
