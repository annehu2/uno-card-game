import java.util.ArrayList;
import java.util.Collections;

/*
 * @author Anne Hu This class controls the actions of the deck
 * Shuffling the deck, getting the deck, getting the discard and deck piles
 */
public class Deck {
	private ArrayList<Card> deck;
	private ArrayList<Card> discard = new ArrayList<Card>();
	private String[] colours = { "Red", "Yellow", "Green", "Blue" };
	private String[] cardTypes = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Skip",
			"Reverse", "AddTwo" };

	/*
	 * Calls new deck method to create a new deck when the deck constructor is
	 * called
	 */
	public Deck() {
		deck = newDeck();
	}

	private ArrayList<Card> newDeck() {
		deck = new ArrayList<Card>();

		// creates two cards of each of the numbers, (except 0), skip, reverse, add two
		for (int i = 0; i < 2; i++) {
			for (String c : colours) {
				for (String t : cardTypes) {
					deck.add(new Card(t, c));
				}
			}
		}

		// creates 4 wild add fours, and 4 wild cards
		for (int i = 0; i < 4; i++) {
			deck.add(new Card("Wild", "Wild"));
			deck.add(new Card("Wild", "AddFour"));
		}

		// creates 1 of each color of "0"
		for (String c : colours) {
			deck.add(new Card("Zero", c));
		}

		shuffleDeck();
		return deck;
	}

	/*
	 * returns the deck when called from game
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	/*
	 * returns the discard pile to the game
	 */
	public ArrayList<Card> getDiscard() {
		return discard;
	}

	/*
	 * returns the last card placed on the discard pile
	 */
	public Card getLastCard() {
		return discard.get(discard.size() - 1);
	}

	/*
	 * shuffles the deck (shuffles the arraylist)
	 */
	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	/*
	 * draws a card from the pile
	 */
	public Card drawCard() {
		Card c = deck.remove(0);
		return c;
	}
}
