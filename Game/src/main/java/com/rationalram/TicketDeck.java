package com.rationalram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketDeck {
	private int ticketsTotalQty = -1;
	private List<Ticket> ticketList = new ArrayList<Ticket>();
	
	private void count() {
		ticketsTotalQty = this.ticketList.size();
	}
	public void clear() {
		this.ticketList.clear();
	}
	public void initShort(){
		int[] points = 	 {8,		7,			6,				5,			7,			8,			9,				8,			7};
		String[] cityA = {"wroclaw","szczecin",	"bydgoszcz",	"poznan",	"lodz",		"szczecin",	"lodz",			"katowice",	"poznan"};
		String[] cityB = {"lublin",	"gdansk",	"bialystok",	"warszawa",	"rzeszow",	"wroclaw",	"bialystok",	"rzeszow",	"katowice"};
		for(int i = 0 ; i < points.length ; i++) {
			Ticket ticket = new Ticket();
			ticket.setId(i);
			ticket.setCityA(cityA[i]);
			ticket.setCityB(cityB[i]);
			ticket.setPoints(points[i]);
			this.ticketList.add(ticket);
			this.shuffle();
		}
		this.count();
	}
	
	public void initLong() {
		
	}
	
	public void shuffle() {
		Random random = new Random();
		for(int i=0 ; i < this.ticketList.size() ; i++) {
			int randomIndex = random.nextInt(this.ticketList.size());
			Ticket tempTicket = new Ticket();
			tempTicket = this.ticketList.get(i);
			this.ticketList.set(i,this.ticketList.get(randomIndex));
			this.ticketList.set(randomIndex, tempTicket);
		}
	}
	public Ticket pick() {
		if(!this.ticketList.isEmpty()) {
			int indexOfLastElement = this.ticketList.size()-1;
			Ticket ticket = this.ticketList.get(indexOfLastElement);
			this.ticketList.remove(indexOfLastElement);
			this.count();
			return ticket;
		}else {
			return null;
		}
	}
	public Ticket pick(int ticketId) {
		if(!this.ticketList.isEmpty()) {
			Ticket ticket = null;
			for(Ticket t:this.getTicketList()) {
				if(t.getId() == ticketId) {
					ticket = t;
				}
			}
			this.ticketList.remove(ticket);
			this.count();
			return ticket;
		}else {
			return null;
		}
	}
	public void put(Ticket ticket) {
		this.ticketList.add(ticket);
		this.count();
	}
	public boolean isEmpty() {
		if(this.ticketList.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean contains(int ticketId) {
		boolean b = false;
		for(Ticket t : this.ticketList) {
			if(t.getId() == ticketId) {b = true;}
			else {}
		}
		return b;
	}
	protected List<Ticket> getTicketList() {
		return ticketList;
	}

	protected void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public int getTicketsTotalQty() {
		return ticketsTotalQty;
	}
	public void setTicketsTotalQty(int ticketsTotalQty) {
		this.ticketsTotalQty = ticketsTotalQty;
	}

}
