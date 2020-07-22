package com.rationalram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Routes {
	private List<Route> routesList = new ArrayList<Route>();
	
	public List<Route> getRoutesList() {
		return routesList;
	}

	public void setRoutesList(List<Route> routesList) {
		this.routesList = routesList;
	}

	public void init() {
		
		File file = new File("C:\\Users\\MariuszBaran\\git\\theGame\\Game\\src\\main\\java\\com\\rationalram\\routes.csv");
		try {
			Scanner scanner = new Scanner(file);
			String header = scanner.nextLine();
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] data = row.split(";");
				Route route = new Route();
				route.setId(Integer.parseInt(data[1]));
				route.setPoints(Integer.parseInt(data[2]));
				route.setCityA(data[6]);
				route.setCityB(data[7]);
				route.setCostColor(Integer.parseInt(data[4]));
				route.setCostSpecial(Integer.parseInt(data[5]));
				route.setRequiredColor(data[3]);
				routesList.add(route);
			}
			scanner.close();
			
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	public Route getRoute(int routeId) {
		Route route = new Route();
		for(Route r: this.routesList) {
			if(r.getId() == routeId)route = r;
		}
		return route;
	}
	
	public boolean contains(Route route) {
		if(this.routesList.contains(route)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void add(Route route) {
		this.routesList.add(route);
	}
	
	public void build(int routeId, Player player, Game game) {
		int actionCost = 12;
		Route route = game.getRoutes().getRoute(routeId);
		//Route free, no owner
		if(route.getOwnedBy().equals(route.getNobody())) {
			int costColor = route.getCostColor();
			int costSpecial = route.getCostSpecial();
			String requiredColor;
			
			if(route.getRequiredColor().contentEquals("neutral")) {
				requiredColor = player.getBuildingDeck().getColor();
			}else {
				requiredColor = route.getRequiredColor();
			}
			
			int playerColor = player.getBuildingDeck().getQtyByColor(requiredColor);
			int playerSpecial = player.getBuildingDeck().getQtyByColor("special");
			int colorToUse = 0;
			int specialToUse = 0;
			int specialAsColor = 0;
			
			if(playerColor < costColor) {
				colorToUse = playerColor;
				specialAsColor = costColor - colorToUse;
			}else {
				colorToUse = costColor;
			}
			
			specialToUse = costSpecial + specialAsColor;
	
			if(colorToUse + specialAsColor >= costColor && playerSpecial - specialAsColor >= costSpecial) {
				route.setOwnedBy(player);
				route.setType(route.getROUTE());
				player.getRoutes().add(route);
				player.increasePoints(route.getPoints());
				player.decreaseActionPointsBy(actionCost);
				player.decreasePawns(costColor+costSpecial);
				
				for(int i = 0; i < colorToUse; i++) {
					Card card = player.getBuildingDeck().pickByColor(requiredColor);
					game.getUsedDeck().put(card);
				}
				
				for(int i = 0; i < specialToUse; i++) {
					Card card = player.getBuildingDeck().pickByColor(player.getBuildingDeck().getColorSpecial());
					game.getUsedDeck().put(card);
				}
				
				if(!player.getBuildingDeck().isEmpty()) {
					int cardQty = player.getBuildingDeck().getCardTotalQty();
					for(int i = 0; i < cardQty; i++) {
						Card card = player.getBuildingDeck().pick();
						player.getHandDeck().put(card);
					}
				}
				
				player.getBuildingDeck().setColor(player.getBuildingDeck().getColorEmpty());
				player.setActionMode(ActionMode.none);				
			}else {
				System.out.println("Not enought proper cards to build this route");
			}
		}else {
			//Route owned by the player
			if(route.getOwnedBy().equals(player)) {
				System.out.println("You already own this route dummy");
			}else {
				//Route owned by different player, build interchange station
				if(player.getRoutes().contains(route)) {
					System.out.println("You already build the interchange stations here, silly you");
				}else {
					System.out.println("building interchange");
					if(player.getInterchangeQty() > 0) {
						int costColor = 999;
						int interchangeQty = player.getInterchangeQty();
						switch(interchangeQty) {
						case 1:
							costColor = 3;
							break;
						case 2:
							costColor = 2;
							break;
						case 3:
							costColor = 1;
							break;
						}
						int costSpecial = 0;
						String requiredColor = player.getBuildingDeck().getColor();
						int playerColor = player.getBuildingDeck().getQtyByColor(requiredColor);
						int playerSpecial = player.getBuildingDeck().getQtyByColor("special");
						int colorToUse = 0;
						int specialToUse = 0;
						int specialAsColor = 0;
						
						if(playerColor < costColor) {
							colorToUse = playerColor;
							specialAsColor = costColor - colorToUse;
						}else {
							colorToUse = costColor;
						}
						
						specialToUse = costSpecial + specialAsColor;
						
						if(colorToUse + specialAsColor >= costColor && playerSpecial - specialAsColor >= costSpecial) {
							route.setType(route.getINTERCHANGE());
							player.getRoutes().add(route);
							player.decreaseActionPointsBy(actionCost);
							player.decreaseInterchangeQtyBy(1);
							
							for(int i = 0; i < colorToUse; i++) {
								Card card = player.getBuildingDeck().pickByColor(requiredColor);
								game.getUsedDeck().put(card);
							}
							
							for(int i = 0; i < specialToUse; i++) {
								Card card = player.getBuildingDeck().pickByColor(player.getBuildingDeck().getColorSpecial());
								game.getUsedDeck().put(card);
							}
							
							if(!player.getBuildingDeck().isEmpty()) {
								int cardQty = player.getBuildingDeck().getCardTotalQty();
								for(int i = 0; i < cardQty; i++) {
									Card card = player.getBuildingDeck().pick();
									player.getHandDeck().put(card);
								}
							}
							
							player.getBuildingDeck().setColor(player.getBuildingDeck().getColorEmpty());
							player.setActionMode(ActionMode.none);				
						}else {
							//do nothing here if Cancel button implemented
							if(!player.getBuildingDeck().isEmpty()) {
								int cardQty = player.getBuildingDeck().getCardTotalQty();
								for(int i = 0; i < cardQty; i++) {
									Card card = player.getBuildingDeck().pick();
									player.getHandDeck().put(card);
								}
							}
							player.setActionMode(ActionMode.none);
							player.getBuildingDeck().setColor(player.getBuildingDeck().getColorEmpty());
							System.out.println("Not enought proper cards to build this interchange");
						}
					}else {
						System.out.println("You do not have interchange stations");
					}	
				}
			}
		}
	}

}
