package com.rationalram;

import java.util.ArrayList;	
import java.util.List;
import java.util.Random;

public class ColorDeck{
	private int redCardQty = -1;
	private int blueCardQty = -1;
	private int greenCardQty = -1;
	private int yellowCardQty = -1;
	private int blackCardQty = -1;
	private int orangeCardQty = -1;
	private int pinkCardQty = -1;
	private int whiteCardQty = -1;
	private int specialCardQty = -1;
	private int cardTotalQty = -1;
	private List<Card> cardList = new ArrayList<Card>();
	private final String colorSpecial = "special";
	private final String colorEmpty = "empty";
	
	public ColorDeck() {
		this.countCards();
	}
	
	//counts cards, sets particular colours quantity and total quantity
	private void countCards() {
		int black=0;
		int red=0;
		int orange=0;
		int yellow=0;
		int blue=0;
		int green=0;
		int pink=0;
		int white=0;
		int special=0;
		
		for(Card c: cardList) {
			switch(c.getColor()) {
			case "black":
				black++;
				break;
			case "red":
				red++;
				break;
			case "orange":
				orange++;
				break;
			case "yellow":
				yellow++;
				break;
			case "blue":
				blue++;
				break;
			case "green":
				green++;
				break;
			case "pink":
				pink++;
				break;
			case "white":
				white++;
				break;
			case "special":
				special++;
				break;
			}
		}
		this.blackCardQty=black;
		this.redCardQty=red;
		this.orangeCardQty=orange;
		this.yellowCardQty=yellow;
		this.blueCardQty=blue;
		this.greenCardQty=green;
		this.pinkCardQty=pink;
		this.whiteCardQty=white;
		this.specialCardQty=special;
		this.cardTotalQty = black+red+orange+yellow+blue+green+pink+white+special;
		
	}
	
	//creates deck of 8x12 colour cards and 14 special cards
	public void initialize() {
		for(Color c: Color.values()) {
			for(int i = 0;i<12;i++) {
				Card card = new Card();
				card.setColor(c.toString());
				card.setActionCost(6);
				this.cardList.add(card);
			}
		}
		for(int i = 0;i<14;i++) {
			Card card = new Card();
			card.setColor("special");
			card.setActionCost(12);
			this.cardList.add(card);
		}
		this.countCards();
	}
	
	//returns card from the top of the deck and removes if from the deck
	public Card pick() {
		if(!this.cardList.isEmpty()) {
			int indexOfLastElement = this.cardList.size()-1;
			Card card = this.cardList.get(indexOfLastElement);
			this.cardList.remove(indexOfLastElement);
			this.countCards();
			return card;
		}else {
			return null;
		}
	}
	
	//returns a card at the index from the deck and and removes it, one card less in deck
	//returns null in case of empty deck, add log later
	public Card pick(int index) {
		boolean indexInRange = false;
		int indexOfLastElement = this.cardList.size()-1;
		if(index >= 0 && index <= indexOfLastElement) {
			indexInRange = true;
		}else {
			indexInRange = false;
		}
		if(!this.cardList.isEmpty() && indexInRange) {
			Card card = this.cardList.get(index);
			this.cardList.remove(index);
			this.countCards();
			return card;			
		}
		else {
			return null;
		}
		
	}
	
	public boolean isEmpty() {
		return this.cardList.isEmpty();
	}
	
	public Card getByIndex(int index) {
		return this.cardList.get(index);
	}
	
	public Card pickByColor(String color) {
		Card card = null;
		for(Card c: this.cardList) {
			if(c.getColor().equals(color)) {
				card = c;
			}
		}
		this.cardList.remove(card);
		this.countCards();
		return card;
	}
	
	public int getQtyByColor(String color) {
		int number = 0;
		switch(color) {
		case "black":
			number = this.getBlackCardQty();
			break;
		case "red":
			number = this.getRedCardQty();
			break;
		case "orange":
			number = this.getOrangeCardQty();
			break;
		case "yellow":
			number = this.getYellowCardQty();
			break;
		case "green":
			number = this.getGreenCardQty();
			break;
		case "blue":
			number = this.getBlueCardQty();
			break;
		case "pink":
			number = this.getPinkCardQty();
			break;
		case "white":
			number = this.getWhiteCardQty();
			break;
		case "special":
			number = this.getSpecialCardQty();
			break;
		}
		return number;
	}
	
	//randomise cards in deck
	public void shuffle() {
		Random random = new Random();
		for(int i=0;i<this.cardList.size();i++) {
			int randomIndex = random.nextInt(this.cardList.size());
			Card tempCard = new Card();
			tempCard = this.cardList.get(i);
			this.cardList.set(i,this.cardList.get(randomIndex));
			this.cardList.set(randomIndex, tempCard);
		}
	}
	
	//puts a card on the top of the deck
	public void put(Card card) {
		this.cardList.add(card);
		this.countCards();
	}
	
	//puts a card in the deck at the position of index, deck expanded
	public void put(int index, Card card) {
		int indexOfLastElement = this.cardList.size()-1;
		if(index >= 0 && index <= indexOfLastElement){
			this.cardList.add(index, card);
			this.countCards();
		}else {
			//some error
		}
		
	}
	public int getRedCardQty() {
		return redCardQty;
	}
	public void setRedCardQty(int redCardQty) {
		this.redCardQty = redCardQty;
	}
	public int getBlueCardQty() {
		return blueCardQty;
	}
	public void setBlueCardQty(int blueCardQty) {
		this.blueCardQty = blueCardQty;
	}
	public int getGreenCardQty() {
		return greenCardQty;
	}
	public void setGreenCardQty(int greenCardQty) {
		this.greenCardQty = greenCardQty;
	}
	public int getYellowCardQty() {
		return yellowCardQty;
	}
	public void setYellowCardQty(int yellowCardQty) {
		this.yellowCardQty = yellowCardQty;
	}
	public int getBlackCardQty() {
		return blackCardQty;
	}
	public void setBlackCardQty(int blackCardQty) {
		this.blackCardQty = blackCardQty;
	}
	public int getOrangeCardQty() {
		return orangeCardQty;
	}
	public void setOrangeCardQty(int orangeCardQty) {
		this.orangeCardQty = orangeCardQty;
	}
	public int getPinkCardQty() {
		return pinkCardQty;
	}
	public void setPinkCardQty(int pinkCardQty) {
		this.pinkCardQty = pinkCardQty;
	}
	public int getSpecialCardQty() {
		return specialCardQty;
	}
	public void setSpecialCardQty(int specialCardQty) {
		this.specialCardQty = specialCardQty;
	}
	public int getCardTotalQty() {
		return cardTotalQty;
	}
	public void setCardTotalQty(int cardTotalQty) {
		this.cardTotalQty = cardTotalQty;
	}
	
	protected List<Card> getCardList() {
		return cardList;
	}
/*
	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
*/
	
	public int getWhiteCardQty() {
		return whiteCardQty;
	}
	public void setWhiteCardQty(int whiteCardQty) {
		this.whiteCardQty = whiteCardQty;
	}

	public String getColorSpecial() {
		return colorSpecial;
	}

	public String getColorEmpty() {
		return colorEmpty;
	}
}
