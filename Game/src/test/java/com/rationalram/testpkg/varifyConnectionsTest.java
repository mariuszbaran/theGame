package com.rationalram.testpkg;
import com.rationalram.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class varifyConnectionsTest {

	@Test
	public void verifyConnectionsTestFullList() {
		
		List<Route> list = new ArrayList<Route>();
		int[] id =					{1,			2,			3,			4,			7,			8,			9,			11,			6,			10,			12,			13,			0,			5};
		int[] points = 	 			{7,			2,			4,			12,			7,			4,			4,			7,			2,			1,			4,			4,			4,			7};
		int[] costColor = 			{4,			2,			3,			6,			4,			2,			3,			4,			0,			1,			3,			3,			3,			4};
		int[] costSpecial = 		{0,			0,			0,			0,			0,			1,			0,			0,			2,			0,			0,			0,			0,			0};
		String[] requiredColor = 	{"black",	"blue",		"white",	"neutral",	"pink",		"red",		"neutral",	"orange",	"blue",		"black",	"yellow",	"green",	"red",		"neutral"};
		String[] cityA = 			{"szczecin","poznan",	"bydgoszcz","gdansk",	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"lodz",		"katowice",	"krakow",	"rzeszow",	"lublin",	"warszawa"};
		String[] cityB = 			{"poznan",	"bydgoszcz","gdansk",	"warszawa",	"lodz",		"wroclaw",	"katowice",	"lodz",		"warszawa",	"krakow",	"rzeszow",	"lublin",	"warszawa",	"bialystok"};
		for(int i = 0 ; i < points.length ; i++) {
			Route route = new Route();
			route.setId(id[i]);
			route.setPoints(points[i]);
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			route.setRequiredColor(requiredColor[i]);
			list.add(route);
		}
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		String endA = "warszawa";
		String endB = "szczecin";
		Ticket ticket = new Ticket();
		ticket.setCityA(endA);
		ticket.setCityB(endB);
		
		Player player = new Player();
		
		boolean result = player.verifyConnection(ticket, routes);
		
		assertEquals(result,true);
	}
	
	@Test
	public void verifyConnectionsTestPositive() {
		
		List<Route> list = new ArrayList<Route>();
		int[] id =					{1,			2,			3,				7,			8,			9,			11,			12,			13,			0,			5};
		int[] points = 	 			{7,			2,			4,				7,			4,			4,			7,			4,			4,			4,			7};
		int[] costColor = 			{4,			2,			3,				4,			2,			3,			4,			3,			3,			3,			4};
		int[] costSpecial = 		{0,			0,			0,				0,			1,			0,			0,			0,			0,			0,			0};
		String[] requiredColor = 	{"black",	"blue",		"white",		"pink",		"red",		"neutral",	"orange",	"yellow",	"green",	"red",		"neutral"};
		String[] cityA = 			{"szczecin","poznan",	"bydgoszcz",	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"krakow",	"rzeszow",	"lublin",	"warszawa"};
		String[] cityB = 			{"poznan",	"bydgoszcz","gdansk",		"lodz",		"wroclaw",	"katowice",	"lodz",		"rzeszow",	"lublin",	"warszawa",	"bialystok"};
		for(int i = 0 ; i < points.length ; i++) {
			Route route = new Route();
			route.setId(id[i]);
			route.setPoints(points[i]);
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			route.setRequiredColor(requiredColor[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		String endA = "krakow";
		String endB = "bialystok";
		Ticket ticket = new Ticket();
		ticket.setCityA(endA);
		ticket.setCityB(endB);
		
		Player player = new Player();
		
		boolean result = player.verifyConnection(ticket, routes);
		
		assertEquals(result,true);
	}
	
	@Test
	public void verifyConnectionsTestNegative() {
		
		List<Route> list = new ArrayList<Route>();
		int[] id =					{1,			2,			3,				7,			8,			9,			11,			12,			13,			0,			5};
		int[] points = 	 			{7,			2,			4,				7,			4,			4,			7,			4,			4,			4,			7};
		int[] costColor = 			{4,			2,			3,				4,			2,			3,			4,			3,			3,			3,			4};
		int[] costSpecial = 		{0,			0,			0,				0,			1,			0,			0,			0,			0,			0,			0};
		String[] requiredColor = 	{"black",	"blue",		"white",		"pink",		"red",		"neutral",	"orange",	"yellow",	"green",	"red",		"neutral"};
		String[] cityA = 			{"szczecin","poznan",	"bydgoszcz",	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"krakow",	"rzeszow",	"lublin",	"warszawa"};
		String[] cityB = 			{"poznan",	"bydgoszcz","gdansk",		"lodz",		"wroclaw",	"katowice",	"lodz",		"rzeszow",	"lublin",	"warszawa",	"bialystok"};
		for(int i = 0 ; i < points.length ; i++) {
			Route route = new Route();
			route.setId(id[i]);
			route.setPoints(points[i]);
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			route.setRequiredColor(requiredColor[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		String endA = "krakow";
		String endB = "katowice";
		Ticket ticket = new Ticket();
		ticket.setCityA(endA);
		ticket.setCityB(endB);
		
		Player player = new Player();
		
		boolean result = player.verifyConnection(ticket, routes);
		
		assertEquals(result,false);
	}

}
