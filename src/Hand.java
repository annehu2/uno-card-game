import java.util.ArrayList;

/**
 * @author Anne Hu 
 * This class controls the players hand, (cards in their hand)
 */
public class Hand {
	// array list of their cards in teir hand
	ArrayList<Card> hand = new ArrayList<Card>(); 

	/*
	 * method that returns the hand to the game
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/*
	 * adds a card to the hand
	 */
	public void addCard(Card card) {
		hand.add(card);
	}

	/*
	 * "Placing the card in the discard pile", in which removes the card form the hand
	 */
	public Card placeCard(int index) {
		return hand.remove(index);
	}

	/*
	 * returning the amount of cards in the players hand
	 */
	public int getSize() {
		return hand.size();
	}

	/*
	 * disables the player from the using the deck
	 */
	public void disableDeck(boolean toDisable) {
		for (Card c : hand) {
			c.getImage().setId("greyOut");
			c.getImage().setDisable(toDisable);
		}
	}
	
	/*
	 * getting the card index in the hand arraylist, passing through the
	 * card as a parameter
	 * returns the index of the card
	 */
	public int getCardIndex(Card c) {
		return getHand().indexOf(c);
	}

}
