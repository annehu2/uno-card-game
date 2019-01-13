import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author Anne Hu
 * This class is the user interface when the user pauses the game
 */
public class PauseWindow {
	/*
	 * displays the pause window
	 */
	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UNDECORATED);

		BorderPane bp = new BorderPane();
		bp.setId("pauseWindow");
		VBox box = new VBox(60);
		bp.setPadding(new Insets(10, 10, 10, 10));

		// adding the uno picture to the top of the border pane
		File file = new File("bin/unoLogo.png");
		Image unoLogo = new Image(file.toURI().toString());
		ImageView iv = new ImageView(unoLogo);
		BorderPane.setAlignment(iv, Pos.CENTER);
		iv.setFitHeight(150);
		iv.setFitWidth(175);
		bp.setTop(iv);

		Button goBackButton = new Button("Continue");
		Button mainButton = new Button("Main Menu");
		goBackButton.setId("pauseWindowButtons");
		mainButton.setId("pauseWindowButtons");
		mainButton.setMinHeight(50);
		mainButton.setMinWidth(200);
		goBackButton.setMinHeight(50);
		goBackButton.setMinWidth(200);

		goBackButton.setOnAction(e -> window.close());
		
		/*
		 * showing the main menu when user clicks on the main menu button
		 */
		mainButton.setOnAction(e ->{
			MainMenu.window.setScene(MainMenu.getScene());
			window.close();
		});

		box.getChildren().addAll(goBackButton, mainButton);
		box.setAlignment(Pos.CENTER);
		bp.setCenter(box);

		Scene scene = new Scene(bp, 400, 400);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);

		window.setOnCloseRequest(e -> {
			e.consume();
		});

		window.showAndWait();
	}

}