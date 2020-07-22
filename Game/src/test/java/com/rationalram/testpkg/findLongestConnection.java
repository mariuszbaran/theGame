package com.rationalram.testpkg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rationalram.Player;
import com.rationalram.Route;
import com.rationalram.Routes;


public class findLongestConnection {

	@Test
	public void testLongest1() { // only one route to test - size 4
		
		int size = 4;
		
		List<Route> list = new ArrayList<Route>();
		int[] id =					{1};//,			2,			3,			4,			7,			8,			9,			11,			6,			10,			12,			13,			0,			5};
		int[] points = 	 			{7};//,			2,			4,			12,			7,			4,			4,			7,			2,			1,			4,			4,			4,			7};
		int[] costColor = 			{4};//,			2,			3,			6,			4,			2,			3,			4,			0,			1,			3,			3,			3,			4};
		int[] costSpecial = 		{0};//,			0,			0,			0,			0,			1,			0,			0,			2,			0,			0,			0,			0,			0};
		String[] requiredColor = 	{"black"};//,	"blue",		"white",	"neutral",	"pink",		"red",		"neutral",	"orange",	"blue",		"black",	"yellow",	"green",	"red",		"neutral"};
		String[] cityA = 			{"szczecin"};//,"poznan",	"bydgoszcz","gdansk",	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"lodz",		"katowice",	"krakow",	"rzeszow",	"lublin",	"warszawa"};
		String[] cityB = 			{"poznan"};//,	"bydgoszcz","gdansk",	"warszawa",	"lodz",		"wroclaw",	"katowice",	"lodz",		"warszawa",	"krakow",	"rzeszow",	"lublin",	"warszawa",	"bialystok"};
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
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}
	
	
	@Test
	public void testLongest2() { //  two separate routes to test - size 4 and 6
		
		int size = 6;
		
		List<Route> list = new ArrayList<Route>();
		int[] id =					{1,			4};//,			7,			8,			9,			11,			6,			10,			12,			13,			0,			5};
		int[] points = 	 			{7,			12};//,			7,			4,			4,			7,			2,			1,			4,			4,			4,			7};
		int[] costColor = 			{4,			6};//,			4,			2,			3,			4,			0,			1,			3,			3,			3,			4};
		int[] costSpecial = 		{0,			0};//,			0,			1,			0,			0,			2,			0,			0,			0,			0,			0};
		String[] requiredColor = 	{"black",	"neutral"};//,	"pink",		"red",		"neutral",	"orange",	"blue",		"black",	"yellow",	"green",	"red",		"neutral"};
		String[] cityA = 			{"szczecin","gdansk"};//,	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"lodz",		"katowice",	"krakow",	"rzeszow",	"lublin",	"warszawa"};
		String[] cityB = 			{"poznan",	"warszawa"};//,	"lodz",		"wroclaw",	"katowice",	"lodz",		"warszawa",	"krakow",	"rzeszow",	"lublin",	"warszawa",	"bialystok"};
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
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}
	
	
	@Test
	public void testLongest3() { //  two connected routes to test - size 4 and 6 = 10
		
		int size = 10;
		
		List<Route> list = new ArrayList<Route>();
	
		int[] costColor = 			{4,			6};
		int[] costSpecial = 		{0,			0};	
		String[] cityA = 			{"bialystok","gdansk"};
		String[] cityB = 			{"warszawa", "warszawa"};
		for(int i = 0 ; i < cityB.length ; i++) {
			Route route = new Route();
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			list.add(route);
		}
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}
	
	
	
	@Test
	public void testLongest4() { //  four connected routes in a branch to test - 4 + 4 and 4 + 2 + 3 
		
		int size = 9;
		
		List<Route> list = new ArrayList<Route>();
		
		int[] costColor = 			{4,				4,			0,			3};
		int[] costSpecial = 		{0,				0,			2,			0};	
		String[] cityA = 			{"bydgoszcz",	"lodz",		"lodz",		"warszawa"};
		String[] cityB = 			{"lodz", 		"katowice",	"warszawa",	"lublin"};
		for(int i = 0 ; i < cityB.length ; i++) {
			Route route = new Route();
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}
	
	
	
	
	@Test
	public void testLongest5() { //  two separated connections of 3 and 4 routes - longest 12
		
		int size = 12;
		
		List<Route> list = new ArrayList<Route>();
		
		int[] costColor = 			{3,3,6,1,3,2,2};
		int[] costSpecial = 		{0,0,0,0,0,1,0};	
		String[] cityA = 			{"warszawa","lublin",	"gdansk",	"krakow",	"katowice","wroclaw","poznan"};
		String[] cityB = 			{"lublin", 	"rzeszow",	"warszawa",	"katowice", "wroclaw", "poznan", "bydgoszcz"};
		for(int i = 0 ; i < cityB.length ; i++) {
			Route route = new Route();
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		assertEquals(result,size);
	}
	
	
	
	
	@Test
	public void testLongest6() { //  two separated connections of 3 and 3 routes interconnected - longest 12
		
		int size = 12;
		
		List<Route> list = new ArrayList<Route>();
		
		int[] costColor = 			{0,4,6,3,4,3};
		int[] costSpecial = 		{2,0,0,0,0,0};	
		String[] cityA = 			{"warszawa","warszawa",	"gdansk",	"warszawa",	"lodz",		"lublin"};
		String[] cityB = 			{"lodz", 	"bialystok","warszawa",	"lublin", 	"katowice", "rzeszow"};
		for(int i = 0 ; i < cityB.length ; i++) {
			Route route = new Route();
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}
	
	
	@Test
	public void testLongest7() { //  connections with a loop and a branch- longest 18
		
		int size = 18;
		
		List<Route> list = new ArrayList<Route>();
		
		int[] costColor = 			{0,4,2,2,3,4,1};
		int[] costSpecial = 		{2,0,0,1,0,0,0};	
		String[] cityA = 			{"warszawa","lodz",		"bydgoszcz","poznan",	"wroclaw",	"katowice",	"katowice"};
		String[] cityB = 			{"lodz",	"bydgoszcz","poznan",	"wroclaw",	"katowice",	"lodz",		"krakow"};
		for(int i = 0 ; i < cityB.length ; i++) {
			Route route = new Route();
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			list.add(route);
		}
		
		Routes routes = new Routes();
		routes.setRoutesList(list);
		
		Player player = new Player();
		
		int result = player.getLongestConnection(routes);
		
		assertEquals(result,size);
	}


}
