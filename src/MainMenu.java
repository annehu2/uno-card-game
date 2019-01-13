import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Anne Hu 2018
 * Unfinished UNO! Card Game
 */
public class MainMenu extends Application {
	ArrayList<Button> buttons = new ArrayList<Button>();
	static Stage window;
	static Scene scene;

	// main method
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * displays the main menu
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		// new layouts, and v and h boxes
		// setting the padding to all the layouts
		BorderPane bp = new BorderPane();
		bp.setId("mainMenuWindow");
		HBox center = new HBox(150);
		VBox box = new VBox(50);
		VBox box2 = new VBox(50);
		VBox buttonBox = new VBox(50);
		bp.setPadding(new Insets(100, 20, 150, 50));

		File file = new File("bin/unoLogo.png");
		Image unoLogo = new Image(file.toURI().toString());
		ImageView iv = new ImageView(unoLogo);
		BorderPane.setAlignment(iv, Pos.CENTER);
		iv.setFitHeight(465);
		iv.setFitWidth(500);

		// new buttons
		Button startGame = new Button("Start New Game");

		box.getChildren().addAll(startGame);

		Button howButton = new Button("How To Play");
		Button exitButton = new Button("Exit");

		box2.getChildren().addAll(howButton, exitButton);

		buttonBox.getChildren().addAll(box, box2);
		addButtons(startGame, howButton, exitButton);

		for (Button b : buttons) {
			b.setId("mainMenuButtons");
			b.setMinHeight(50);
			b.setMinWidth(300);
		}

		// when exit button is clicked
		exitButton.setOnAction(e -> window.close());

		// when start button is clicked
		startGame.setOnAction(e -> {
			Game.refresh();
			window.setScene(Game.start());
		});
		
		// when how to play button is clicked
		howButton.setOnAction(e->{
			window.setScene(HowToPlayWindow.display());
		});

		// adding ui to the center
		buttonBox.setAlignment(Pos.CENTER);
		center.getChildren().addAll(iv, buttonBox);
		bp.setCenter(center); // setting the center

		scene = new Scene(bp, 1100, 700);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		window.show();
	}

	/*
	 * adding buttons to the array list
	 */
	private void addButtons(Button btn, Button btn2, Button btn3) {
		buttons.add(btn);
		buttons.add(btn2);
		buttons.add(btn3);
	}

	/*
	 * returning the scene
	 */
	public static Scene getScene() {
		return scene;
	}

}