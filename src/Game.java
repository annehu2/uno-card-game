
import java.io.File;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * @author Anne Hu
 * This class is the main UNO game! It has the controls of the human player and the computer player
 */
public class Game {
	static Stage window; // the stage
	static int numOfPlayers = 0; // number of players
	static ArrayList<Hand> hands = new ArrayList<Hand>(); // arraylist for the card hands
	static ArrayList<HBox> bottomBoxes = new ArrayList<HBox>(); // arraylist for the hboxes in the bottom of the screen
	static String[] colours = { "Red", "Yellow", "Green", "Blue" }; // array of all the colour
	static File file = new File("bin/arrow.png"); // arrow image
	static Image arrowImg = new Image(file.toURI().toString());
	static AnchorPane playerCardsLayout, compLayout; // anchorpanes
	static BorderPane root; // border pane
	static Deck deck; // the deck
	static StackPane discardCards; // the stackpane with the discard cards
	static int playerNum, temp; // ints the player number and a temporary int variable
	static int direction = -1; // the direction of play
	static boolean skip; // skipping the next player
	static Button deckButton; // the deck button 
	static ImageView arrow; // the arrow
	static Rectangle rect; // the rectangle rect

	/*
	 * refreshing the game play, when the user wants to start a new game
	 * re deals the cards, and draws the cards
	 */
	public static void refresh() {
		numOfPlayers = HowManyPlayersWindow.display();

		if (root != null) {
			rect = new Rectangle(60, 60);
			deck = new Deck();
			deck.getDeck();
			dealCards();
			drawCards();
			drawComputerPlay(numOfPlayers);
		}
	}

	/*
	 * this method displays all the GUI of the main game
	 * return scene so the stage from the main menu can display this scene
	 */
	public static Scene start() {

		// new borderpane
		root = new BorderPane();
		playerCardsLayout = new AnchorPane();
		compLayout = new AnchorPane();

		root.setPadding(new Insets(10, 10, 10, 10));
		root.setId("background");

		StackPane table = new StackPane();
		discardCards = new StackPane();

		// this is the table and setting the styles of the table
		Ellipse tableSurface = new Ellipse(0, 0, 250, 150);
		tableSurface.setFill(Color.web("FF8000"));
		tableSurface.setStroke(Color.BLACK);
		tableSurface.setStrokeWidth(3);

		// new Hboxes and vboxes that will be placed on the screen
		HBox cardsBox = new HBox(50);
		VBox discardBox = new VBox(10);
		HBox deckBox = new HBox(40);
		VBox space = new VBox(20);
		VBox colourBox = new VBox(10);

		//rectangle colour to show the current colour of the cards
		rect = new Rectangle(60, 60);
		Label colourLbl = new Label("Current Colour:");
		drawArrow();

		 //getting the children and adding them to vertcial colour box
		colourBox.getChildren().addAll(colourLbl, rect);
		colourBox.setAlignment(Pos.CENTER);

		// deck button ui elements
		deckButton = new Button();
		deckButton.setMinWidth(90);
		deckButton.setMinHeight(140);
		deckButton.setId("deckButton");
		deck = new Deck(); // new deck

		// if the deck button is pressed
		// adding a card to the players hand
		deckButton.setOnAction(e -> {
			Card c = deck.drawCard();
			hands.get(0).addCard(c);
			drawCards();
			computerPlay(getDirection(numOfPlayers), numOfPlayers);
		});
		deckButton.setDisable(true);

		// Draws a card for the starting card, if that card is is a Wild Card, than it
		// will redraw
		Card startingCard = deck.drawCard();
		while (startingCard.getColour().equals("Wild") || startingCard.getName().equals("Wild")) {
			startingCard = deck.drawCard();
		}
		// adding children to the boxes, and setting their alignmeents to center
		setCurrentColour(startingCard.getColour());
		discardCards.getChildren().add(startingCard.draw());
		discardBox.getChildren().addAll(discardCards, arrow);
		discardBox.setAlignment(Pos.CENTER);
		deckBox.getChildren().addAll(deckButton, colourBox);
		deckBox.setAlignment(Pos.CENTER);
		space.getChildren().addAll(deckBox, new Label("  "));
		space.setAlignment(Pos.CENTER);
		cardsBox.getChildren().addAll(discardBox, space);
		cardsBox.setAlignment(Pos.CENTER);
		table.getChildren().addAll(tableSurface, cardsBox);
		VBox center = new VBox(100);
		center.getChildren().addAll(compLayout, table);
		root.setCenter(center);

		//setting the buttons to button elements width and height
		Button unoBtn = new Button("UNO!");
		unoBtn.setMinHeight(80.0);
		unoBtn.setMinWidth(80.0);
		unoBtn.setId("unoButton");
		Button pauseBtn = new Button("Pause");
		pauseBtn.setId("pauseButton");
		pauseBtn.setMinHeight(40.0);
		pauseBtn.setMinWidth(60.0);
		VBox buttons = new VBox(10);
		buttons.getChildren().addAll(unoBtn, pauseBtn);
		buttons.setAlignment(Pos.CENTER);
		HBox bottomBox = new HBox(10);
		bottomBox.getChildren().addAll(buttons, playerCardsLayout);

		//when the pause button is bring pressed
		pauseBtn.setOnAction(e -> {
			PauseWindow.display();
		});

		dealCards(); // dealing the cards to the players
		drawCards(); // drawing the cards in the players hand// 
		root.setBottom(bottomBox);
		drawComputerPlay(numOfPlayers); // drawing the computer player to display cards
		Scene scene = new Scene(root, 1100, 700); // new scene
		scene.getStylesheets().add("style.css");

		/*
		 * when a click is made in the scene
		 * and the user clicks on an image view of a card
		 * get the image of the image view and play human player moves
		 * than allow the computer to play their moves
		 */
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				for (Card c : hands.get(0).getHand()) {
					c.getImage().setOnMouseClicked(e -> {
						humanPlayer(c);
						setCurrentColour(c.getColour());
						playMoves(c, getDirection(numOfPlayers), numOfPlayers);
						playerNum = computerPlay(getDirection(numOfPlayers), numOfPlayers);
					});
				}
			}
		});

		// returning the scene
		return scene;
	}

	/*
	 * setting the rect to the colour of the cards being played
	 */
	private static void setCurrentColour(String colour) {
		if (colour.equals("Red")) {
			rect.setFill(Color.RED);
		} else if (colour.equals("Blue")) {
			rect.setFill(Color.BLUE);
		} else if (colour.equals("Green")) {
			rect.setFill(Color.GREEN);
		} else if (colour.equals("Yellow")) {
			rect.setFill(Color.YELLOW);
		}
	}

	/*
	 * drawing an arrow on the screen
	 */
	private static void drawArrow() {
		arrow = new ImageView(arrowImg);
		arrow.setFitHeight(30);
		arrow.setFitWidth(50);
	}

	/*
	 * human player moves, passing through the card that human player played
	 * getting the card that was placed from the human players hand
	 * drawing the discard pile
	 * if the arraylist of type hand is empty
	 * displaying the winner window
	 * peforming certain actions dependant on the type of card they placed
	 */
	public static void humanPlayer(Card c) {
		deck.getDiscard().add(hands.get(0).placeCard(hands.get(0).getCardIndex(c)));
		drawCards();
		drawDiscard(-40, 40, c);
		if (hands.get(0).getHand().isEmpty()) {
			WinnerWindow.display("You Win!", "winnerWindow");
		}
		hands.get(0).disableDeck(true);
		if (c.getColour().equals("AddFour")) {
			c.changeName(ChooseColour.display());
			setCurrentColour(c.getColour());
			pickUpCards(getDirection(numOfPlayers), 4, numOfPlayers);
		} else if (c.getName().equals("Wild")) {
			c.changeColour(ChooseColour.display());
			setCurrentColour(c.getColour());
		}
	}

	/*
	 * getting the direction of the play
	 */
	private static int getDirection(int playerAmt) {
		if (direction < 0) {
			return playerAmt - 1;
		}
		return 1;
	}

	/*
	 * playing the moves, and making sure the computer plays the correct
	 * depending on the card, play the following actions
	 * checking the name of the card, and if its that certain type, perform the action that applies to the type of card
	 */
	public static String playMoves(Card c, int player, int playerAmt) {
		if (c.getName().equals("Reverse")) {
			direction = direction * -1;
			if (direction > 0) {
				arrow.setRotate(180.0);
			} else {
				drawArrow();
			}
		} else if (c.getName().equals("Skip")) {
			skip = true;
		} else if (c.getName().equals("AddTwo")) {
			pickUpCards(player, 2, playerAmt);
		} else if (c.getColour().equals("AddFour")) {
			pickUpCards(player, 4, playerAmt);
			c.changeColour(computerChooseColour());
		} else if (c.getName().equals("Wild")) {
			c.changeColour(computerChooseColour());
		}
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(1000 * player), ae -> setCurrentColour(c.getColour())));
		timeline.play();
		return "";

	}

	/*
	 * computer choosing a random colour when wild card is played
	 */
	private static String computerChooseColour() {
		return colours[(int) (Math.random() * 3 - 0)];
	}

	/*
	 * adding cards to the next player if a pick up card was played
	 * checking for the direction of the card, and determine the next player
	 * drawing the amount of cards needed from the deck
	 */
	private static void pickUpCards(int player, int numOfCards, int playerAmt) {
		int num = 1;
		if ((direction > 0 && player == playerAmt - 1) || (direction < 0 && player == 1)) {
			num = 0;
		}
		for (int i = 0; i < numOfCards; i++) {
			hands.get(num * (player + direction)).getHand().add(deck.drawCard());
		}

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 * (playerAmt)), ae -> drawCards()));
		timeline.play();

	}

	/*
	 * checking if the player sent through as a parameter has the correct card
	 */
	private static boolean hasCard(int player) {
		for (Card c : hands.get(player).getHand()) {
			if (Player.correctCard(deck.getLastCard(), c)) {
				temp = hands.get(player).getCardIndex(c);
				deck.getDiscard().add(c);
				return true;
			}
		}
		return false;
	}

	/*
	 * pause method (timeline animation)
	 */
	private static Timeline pause(Card c, int player, int playerAmt) {
		Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000 * player), ae -> drawDiscard(160, 200, c)),
				new KeyFrame(Duration.millis(1000 * player), ae -> drawComputerPlay(playerAmt)));
		return timeline2;
	}

	/*
	 * Method that controls the computer play
	 */
	private static int computerPlay(int player, int playerAmt) {
		hands.get(0).disableDeck(true); // disabling the deck
		deckButton.setDisable(true);

		/*
		 * if skip is not true, run the following code
		 */
		if (!(skip)) {
			if (hasCard(player)) { // if the player has the card
				Card c = hands.get(player).placeCard(temp); // place the card  in the discard pile
				playMoves(c, player, playerAmt); // play the actions of the card
				pause(c, player, playerAmt).play(); // pause (timeline)
			} else {
				//if they dont have the card
				// draw a card from the deck
				// and redraw the computers playing
				Timeline timeline = new Timeline(
						new KeyFrame(Duration.millis(1000 * player), ae -> hands.get(player).addCard(deck.drawCard())),
						new KeyFrame(Duration.millis(1000 * player), ae -> drawComputerPlay(playerAmt)));
				timeline.play();
			}
		}

		/*
		 * disabling the deck and the computers hands
		 */
		Timeline timeline3 = new Timeline(
				new KeyFrame(Duration.millis(1000 * playerAmt), ae -> hands.get(0).disableDeck(false)));
		timeline3.play();

		Timeline timeline4 = new Timeline(
				new KeyFrame(Duration.millis(1000 * playerAmt), ae -> deckButton.setDisable(false)));
		timeline4.play();

		/*
		 * if the computers hands are empty than the human player has lost
		 */
		if (hands.get(player).getHand().isEmpty()) {
			WinnerWindow.display("You Lose :(", "loserWindow");
		}

		// skipping the turns of next player if skip was played
		// determining the direction to see what player will need to be skipped
		if ((direction > 0 && (player == playerAmt - 1 && skip)) || (direction < 0 && (player == 1 && skip))) {
			if (direction < 0) {
				temp = playerAmt - 1;
			} else if (direction > 0) {
				temp = 1;
			}
			skip = false;
			deckButton.setDisable(true);
			return computerPlay(temp, playerAmt); // play computer play if human plyaer is to be skipped over
		} else {
			if ((direction > 0 && player == playerAmt - 1) || (direction < 0 && player == 1)) {
				if (!(skip)) {
					return 0; // go back to computer play
				}
			}

			// replay method until computer players run out
			Timeline timeline5 = new Timeline(new KeyFrame(Duration.millis(1000 * player), ae -> skip = false),
					new KeyFrame(Duration.millis(1000 * player), ae -> computerPlay(player + direction, playerAmt)));

			timeline5.play();
		}
		return 0;
	}

	/*
	 * dealing the cards out, making a new hand for the number of players
	 * drawing a card from the deck and adding it to a players hand
	 * adding hands to an arraylist called hands
	 */
	private static void dealCards() {
		for (int i = 0; i < numOfPlayers; i++) {
			Hand hand = new Hand();
			for (int j = 0; j < 7; j++) {
				hand.addCard(deck.drawCard());
			}
			hands.add(hand);
		}
	}

	/*
	 * drwing the discard pile
	 * Parameters: min max (to set the rotation) and the card that was removed from a hand to
	 * place at the top of the discard pile
	 */
	private static void drawDiscard(int min, int max, Card c) {
		c.flipCardOver(false);
		c.draw().setRotate((double) (Math.random() * max - min));
		discardCards.getChildren().add(c.getImage());

	}

	/*
	 * draw the human player cards hand
	 * clears the layout, than redraws the cards, to show the new hand
	 * 700 pixels for the human players cards, divide this space by the
	 * number of cards to space out the cards
	 */
	private static void drawCards() {
		playerCardsLayout.getChildren().clear();
		double space = 100;
		for (Card c : hands.get(0).getHand()) {
			AnchorPane.setLeftAnchor(c.draw(), space);
			playerCardsLayout.getChildren().add(c.getImage());
			space = space + 700 / hands.get(0).getSize();
		}
	}

	/*
	 * Drawing the computer play
	 * draws the computers playing the cards
	 * add the horizontal space between the computers
	 */
	private static void drawComputerPlay(int playerAmt) {
		compLayout.getChildren().clear();
		double horiSpace = 1100 / playerAmt;
		double placeH = getPlaceH(playerAmt, horiSpace);

		for (int i = 1; i < playerAmt; i++) {
			double space = 300;
			for (Card c : hands.get(i).getHand()) {
				AnchorPane ap = new AnchorPane();
				c.draw();
				c.flipCardOver(true);
				AnchorPane.setLeftAnchor(c.getImage(), space);
				c.getImage().setFitWidth(55);
				c.getImage().setFitHeight(90);
				ap.getChildren().add(c.getImage());
				space = space + 100 / hands.get(i).getSize();
				compLayout.getChildren().add(ap);
				AnchorPane.setLeftAnchor(ap, placeH);
			}
			placeH = placeH + horiSpace;
		}

	}

	/*
	 * placing the computer players in an organized fashion paramters : the amount
	 * of the places, and the horizontal space the computer players can be in place
	 * one player in the midde, and the rest of the players to the left or right
	 */
	private static double getPlaceH(int playerAmt, double horiSpace) {
		double horiPlace = (horiSpace / 2) - 200;
		if (playerAmt == 2) {
			horiPlace = (horiSpace / 2) - 100;
		} else if (playerAmt == 4 || playerAmt == 5) {
			horiPlace = (horiSpace / 2) - 250;
		}
		return horiPlace;
	}

}// end
