
/*
 * @author Anne Hu Class that compares the last card placed, and the new card to be placed to make sure the
 * computer places the correct card
 */
public class Player {

	/*
	 * parameters is the old card and the new card to be placed
	 * If they re the same colour, OR the same number or a wild card
	 * than it will return true, thus allowingn the computer player to play that card
	 */
	public static boolean correctCard(Card old, Card placed) {
		if (sameColour(old, placed) || sameNum(old, placed) || checkWild(old,placed) ) {
			return true;
		}
		return false;
	}
	

	/*
	 * Method that compares the old card and new card, gtting their colours
	 * and comparing them
	 * Returns true or false
	 */
	private static boolean sameColour(Card old, Card placed) {
		if (old.getColour().equals(placed.getColour())) {
			return true;
		}

		return false;
	}

	/*
	 * Getting their numbers and comparing them
	 */
	private static boolean sameNum(Card old, Card placed) {
		if (old.getName().equals(placed.getName())) {
			return true;
		}

		return false;
	}
	
	/*
	 * if the new card that is placed is wild, than they can place it
	 */
	private static boolean checkWild(Card old, Card placed) {
		if (placed.getColour().equals("Wild") || placed.getColour().equals("AddFour") || placed.getName().equals("Wild") ) {
			return true;
		}
		return false;
	}


}
