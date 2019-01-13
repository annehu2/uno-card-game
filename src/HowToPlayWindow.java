import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/*
 * @author Anne Hu
 * How to play window! Describes how to play the game
 */
public class HowToPlayWindow {
	/*
	 * Array lists that will store the titles and instructions
	 */
	static ArrayList<Label> labels = new ArrayList<Label>();
	static ArrayList<Label> instructions = new ArrayList<Label>();
	static String[] typeLabels = { "How To Play", "Skip", "Draw Two", "Reverse", "Wild", "Wild Draw 4" };

	/*
	 * Display and return a scene to display
	 */
	public static Scene display() {

		BorderPane bp = new BorderPane();
		bp.setId("pauseWindow");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		bp.setPadding(new Insets(10, 10, 40, 10));
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(50);
		grid.setVgap(30);

		Button btn = new Button("Main Menu");
		btn.setId("pauseWindowButtons");
		BorderPane.setAlignment(btn, Pos.CENTER);
		bp.setBottom(btn);

		btn.setOnAction(e -> {
			MainMenu.window.setScene(MainMenu.getScene());
		});

		// add a new label to the array list
		for (String s : typeLabels) {
			labels.add(new Label(s));
		}

		//add a string to the instructions from the file from the instructions
		for (String s : ReadFile.getFileInfo("instructions", 0)) {
			instructions.add(new Label(s));
		} 
		
		for (Label lb : instructions) {
			lb.setId("howToPlayIns");
			lb.setMinWidth(300);
			lb.setMinHeight(50);
			lb.setWrapText(true);
		}
		
		for (Label lb: labels) {
			lb.setId("howToPlayTitle");
		}

		Card skipCard = new Card("Skip", "Blue");
		grid.add(skipCard.draw(), 0, 0);
		grid.add(labels.get(1), 0, 1);
		grid.add(instructions.get(1), 0, 2);
		
		Card drawTwo = new Card("AddTwo", "Red");
		grid.add(drawTwo.draw(), 0, 3);
		grid.add(labels.get(2), 0, 4);
		grid.add(instructions.get(2), 0, 5);
		
		Card reverse = new Card("Reverse", "Green");
		grid.add(reverse.draw(), 2, 0);
		grid.add(labels.get(3), 2, 1);
		grid.add(instructions.get(3), 2, 2);
		
		Card wild = new Card("Wild", "Wild");
		grid.add(wild.draw(), 1, 0);
		grid.add(labels.get(4), 1, 1);
		grid.add(instructions.get(4), 1, 2);
		
		Card wildAddFour = new Card("Wild", "AddFour");
		grid.add(wildAddFour.draw(), 1, 3);
		grid.add(labels.get(5), 1, 4);
		grid.add(instructions.get(5), 1, 5);

		bp.setCenter(grid);
		Scene scene = new Scene(bp, 1100, 700);
		scene.getStylesheets().add("style.css");

		return scene;
	}

}