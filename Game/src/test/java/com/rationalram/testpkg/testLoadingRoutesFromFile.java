package com.rationalram.testpkg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.rationalram.Route;


import com.rationalram.Routes;

public class testLoadingRoutesFromFile {
	@Test
	public void testLoadingRoutes() { 
		
		
		Routes routes = new Routes();
		
		System.out.print(routes.getRoutesList());
		
		routes.init();
		
		System.out.println("id     points   reqColor   cc    cs     cityA     cityB");
		for(Route r: routes.getRoutesList()) {
			System.out.println(r.getId() +" "+ r.getPoints() +" "+ r.getRequiredColor() +" "
		+ r.getCostColor() +" "+ r.getCostSpecial() +" "+ r.getCityA() +" "+ r.getCityB());
		}
		
		//assertEquals(result,size);
	}
}
