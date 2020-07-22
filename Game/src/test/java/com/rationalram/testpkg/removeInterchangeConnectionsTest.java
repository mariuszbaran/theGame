package com.rationalram.testpkg;

import static org.junit.Assert.*;

import java.util.List;

import com.rationalram.Routes;
import org.junit.Test;
import com.rationalram.Route;
import com.rationalram.Player;

public class removeInterchangeConnectionsTest {

	@Test
	public void test1() {
		Routes routes = new Routes();
		List<Route> routesList = routes.getRoutesList();
		String ROUTE = new Route().getROUTE();
		String INTER = new Route().getINTERCHANGE();
		int[] id =					{1,			2,			3,			4,			7			};
		int[] points = 	 			{7,			2,			4,			12,			7			};
		int[] costColor = 			{4,			2,			3,			6,			4			};
		int[] costSpecial = 		{0,			0,			0,			0,			0			};
		String[] requiredColor = 	{"black",	"blue",		"white",	"neutral",	"pink"		};
		String[] cityA = 			{"szczecin","poznan",	"bydgoszcz","gdansk",	"bydgoszcz"};
		String[] cityB = 			{"poznan",	"bydgoszcz","gdansk",	"warszawa",	"lodz"		};
		String[] type = 			{ROUTE,		INTER,		INTER,		ROUTE,		ROUTE		};
		for(int i = 0 ; i < points.length ; i++) {
			Route route = new Route();
			route.setId(id[i]);
			route.setPoints(points[i]);
			route.setCityA(cityA[i]);
			route.setCityB(cityB[i]);
			route.setCostColor(costColor[i]);
			route.setCostSpecial(costSpecial[i]);
			route.setRequiredColor(requiredColor[i]);
			route.setType(type[i]);
			routesList.add(route);
		}
		
		Player player = new Player();
		
		for(Route r: routesList) {
			System.out.println(r.getCityA() + " " + r.getCityB() + " " + r.getType());	
		}
		System.out.println();
		player.removeInterchangeConnections(routesList);
		
		for(Route r: routesList) {
			System.out.println(r.getCityA() + " " + r.getCityB() + " " + r.getType());	
		}
		assertEquals(3,routesList.size());
	}

}
