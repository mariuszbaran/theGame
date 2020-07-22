package com.rationalram;

//cards that are visible so the player can pick two colour or one special
public class OpenDeck extends ColorDeck{
	private int maxCardQty = -1;
	private int maxSpecialCardQty = -1;
	
	//picks a card from open deck and leave empty spot (new card colour = empty)
	@Override
	public Card pick(int index) {
		Card card = new Card();
		Card emptyCard = new Card();
		card = this.getCardList().get(index);
		emptyCard.setColor("empty");
		this.getCardList().set(index, emptyCard);
		return card;
	}
	
	//put new cards from table deck to open deck in empty spots 
	public void topup(ColorDeck td) {
		for(Card card : this.getCardList()) {
			if(card.getColor().equals("empty")) {
				if(!td.isEmpty()) {
					int index = this.getCardList().indexOf(card);
					this.getCardList().set(index, td.pick());
				}else{
					
				}
			}
		}
	}
	
	public int getMaxCardQty() {
		return maxCardQty;
	}
	public void setMaxCardQty(int maxCardQty) {
		this.maxCardQty = maxCardQty;
	}
	public int getMaxSpecialCardQty() {
		return maxSpecialCardQty;
	}
	public void setMaxSpecialCardQty(int maxSpecialCardQty) {
		this.maxSpecialCardQty = maxSpecialCardQty;
	}
}
