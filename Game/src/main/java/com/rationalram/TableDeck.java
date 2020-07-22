package com.rationalram;

public class TableDeck extends ColorDeck{

	public void topupFrom(ColorDeck deck) {
		if(!deck.isEmpty()) {
			TableDeck tempDeck = new TableDeck();
			
			int tdSize = this.getCardTotalQty();
			for(int i = 0; i < tdSize; i++) {
				tempDeck.put(this.pick());
			}
			
			int udSize = deck.getCardTotalQty();
			for(int i = 0; i < udSize; i++) {
				this.put(deck.pick());
			}
			this.shuffle();
			this.shuffle();
			
			int tempSize = tempDeck.getCardTotalQty();
			for(int i = 0; i < tempSize; i++) {
				this.put(tempDeck.pick());
			}
		}
	}
}
