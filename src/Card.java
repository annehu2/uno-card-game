import java.io.File;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author wrightty Creates a playing card with a name, suit, value, and image
 *         The playing card can in or out of the deck The playing card can be
 *         face down or face up (image changes accordingly)
 * 
 */
public class Card {
	private String colour;
	private String name;
	private boolean inDeck = true;
	private int imageXLocation;
	private int imageYLocation;
	private int imageCardHeight = 360;
	private int imageCardWidth = 240;
	private static File file = new File("bin/cardsFront.png");
	private static Image image = new Image(file.toURI().toString());
	private ImageView view;
	private boolean faceDown = false;

	/**
	 * Creates the card object and sets its image location based on its name and
	 * suit.
	 * 
	 * @param name
	 *            of the card (i.e. "King", "Nine")
	 * @param suit
	 *            (i.e. "Clubs", "Spades")
	 * @param value
	 *            of card (game specific)
	 */
	public Card(String name, String suit) {
		this.name = name;
		this.colour = suit;
		setImageLocation();
	}

	public String getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String printOut() {
		return name + " of " + colour;
	}

	public void setInDeck(boolean status) {
		inDeck = status;
	}

	/**
	 * If the card is not supposed to be face down, creates an image of the
	 * individual card (face up) otherwise, creates an image using the back of the
	 * card
	 * 
	 * @return an ImageView object of the individual card.
	 */
	public ImageView draw() {
		if (!faceDown) {
			view = new ImageView(image);
			Rectangle2D viewportRect = new Rectangle2D(getImageX(), getImageY(), imageCardWidth, imageCardHeight);
			view.setViewport(viewportRect);
		} else {
			view = new ImageView(new Image(new File("bin/cardBack.png").toURI().toString()));
		}
		view.setFitHeight(140);
		view.setFitWidth(90);
		return view;
	}

	public ImageView getImage() {
		return view;
	}

	/**
	 * Using the Card's name attribute, an image x location is given Using the
	 * Card's suit attribute, an image y location is given
	 */
	private void setImageLocation() {
		switch (name) {
		case "Zero":
			imageXLocation = 0;
			break;
		case "One":
			imageXLocation = imageCardWidth;
			break;
		case "Two":
			imageXLocation = imageCardWidth * 2;
			break;
		case "Three":
			imageXLocation = imageCardWidth * 3;
			break;
		case "Four":
			imageXLocation = imageCardWidth * 4;
			break;
		case "Five":
			imageXLocation = imageCardWidth * 5;
			break;
		case "Six":
			imageXLocation = imageCardWidth * 6;
			break;
		case "Seven":
			imageXLocation = imageCardWidth * 7;
			break;
		case "Eight":
			imageXLocation = imageCardWidth * 8;
			break;
		case "Nine":
			imageXLocation = imageCardWidth * 9;
			break;
		case "Skip":
			imageXLocation = imageCardWidth * 10;
			break;
		case "Reverse":
			imageXLocation = imageCardWidth * 11;
			break;
		case "AddTwo":
			imageXLocation = imageCardWidth * 12;
			break;
		case "Wild":
			imageXLocation = imageCardWidth * 13;
			break;
		default:
			System.out.println("Card Name Error");
			System.exit(0);
		}

		switch (colour) {
		case "Wild":
			imageYLocation = 0;
			break;
		case "Red":
			imageYLocation = 0;
			break;
		case "Yellow":
			imageYLocation = imageCardHeight;
			break;
		case "Green":
			imageYLocation = imageCardHeight * 2;
			break;
		case "Blue":
			imageYLocation = imageCardHeight * 3;
			break;
		case "AddFour":
			imageYLocation = imageCardHeight * 4;
		}

	}

	/**
	 * flips over the card and redraws it to reset its ImageView
	 */
	public void flipCardOver(boolean flip) {
		if (flip) {
			faceDown = true;
		} else {
			faceDown = false;
		}
		draw();
	}

	private int getImageX() {
		return imageXLocation;
	}

	private int getImageY() {
		return imageYLocation;
	}

	public void disable(boolean toDisable) {
		getImage().setId("greyOut");
		getImage().setDisable(toDisable);

	}

	public void changeColour(String newColour) {
		this.colour = newColour;
	}
	
	public void changeName(String newName) {
		this.colour = newName;
	}


}
