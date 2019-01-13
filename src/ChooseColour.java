import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author Anne Hu Class that displays window to allow human player to choose a new colour
 * for the next player to place
 */
public class ChooseColour {
	static String colour;

	/*
	 * displays the user interface
	 */
	public static String display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UNDECORATED);

		window.setTitle("Choose New Colour");
		window.setMinWidth(250);
		GridPane grid = new GridPane();

		Button redButton = new Button("Red");
		redButton.setMinWidth(150);
		redButton.setMinHeight(100);
		redButton.setId("redButton");
		GridPane.setConstraints(redButton, 0, 0);

		Button yellowButton = new Button("Yellow");
		yellowButton.setMinWidth(150);
		yellowButton.setMinHeight(100);
		yellowButton.setId("yellowButton");
		GridPane.setConstraints(yellowButton, 1, 0);

		Button blueButton = new Button("Blue");
		blueButton.setMinWidth(150);
		blueButton.setMinHeight(100);
		blueButton.setId("blueButton");
		GridPane.setConstraints(blueButton, 0, 1);

		Button greenButton = new Button("Green");
		greenButton.setMinWidth(150);
		greenButton.setMinHeight(100);
		greenButton.setId("greenButton");
		GridPane.setConstraints(greenButton, 1, 1);

		//if the button is pressed, it will set the insrance vairble to colour and
		// close the window afterwards
		redButton.setOnAction(e -> {
			colour = "Red";
			window.close();
		});

		yellowButton.setOnAction(e -> {
			colour = "Yellow";
			window.close();
		});

		blueButton.setOnAction(e -> {
			colour = "Blue";
			window.close();
		});
		greenButton.setOnAction(e -> {
			colour = "Green";
			window.close();
		});

		grid.getChildren().addAll(redButton, yellowButton, greenButton, blueButton);
		Scene scene = new Scene(grid, 300, 200);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);

		/*
		 * we will take care of the cloing of the screen
		 */
		window.setOnCloseRequest(e -> {
			e.consume();
		});

		window.showAndWait();
		return colour;
	}

}
