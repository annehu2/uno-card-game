import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Window that determines how many players the user would like
 */
public class HowManyPlayersWindow {
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static int players;

	/*
	 * Displays the player window
	 *  Returns an integer to the game, to draw that many players
	 */
	public static int display() {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UNDECORATED);

		BorderPane bp = new BorderPane();
		bp.setId("playerWindow");
		bp.setPadding(new Insets(10, 0, 0, 0));
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 0, 0, 0));
		
		Label title = new Label("How Many Players?");
		title.setId("playerWindowTitle");
		BorderPane.setAlignment(title, Pos.CENTER);
		bp.setTop(title);

		Button button2 = new Button("Two");
		GridPane.setConstraints(button2, 0, 0);
		button2.setId("redButton");

		Button button3 = new Button("Three");
		GridPane.setConstraints(button3, 1, 0);
		button3.setId("blueButton");

		Button button4 = new Button("Four");
		GridPane.setConstraints(button4, 0, 1);
		button4.setId("greenButton");

		Button button5 = new Button("Five");
		GridPane.setConstraints(button5, 1, 1);
		button5.setId("yellowButton");

		addButtons(button2, button3, button4, button5);

	/*
	 * Depending on how many players the user would like, it would set the "players" variable
	 * to that number
	 * This number returns, and the window is closed
	 */
		for (Button b : buttons) {
			b.setMinWidth(150);
			b.setMinHeight(100);
		}

		button2.setOnAction(e -> {
			players = 2;
			window.close();
		});

		button3.setOnAction(e -> {
			players = 3;
			window.close();
		});

		button4.setOnAction(e -> {
			players = 4;
			window.close();
		});
		button5.setOnAction(e -> {
			players = 5;
			window.close();
		});

		grid.getChildren().addAll(button2, button3, button4, button5);
		bp.setCenter(grid);
		Scene scene = new Scene(bp, 314, 257);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);

		/*
		 * We will take are of the closing. Only if they have selected players they can continue
		 */
		window.setOnCloseRequest(e -> {
			e.consume();
		});

		window.showAndWait();
		return players;
	}

	/*
	 * Adding buttons to an arraylist
	 * Parameters include all the buttons that are through
	 */
	private static void addButtons(Button btn, Button btn2, Button btn3, Button btn4) {
		buttons.add(btn);
		buttons.add(btn2);
		buttons.add(btn3);
		buttons.add(btn4);
	}
}
