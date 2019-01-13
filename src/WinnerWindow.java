import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author Anne Hu
 * class displays the outcome of the game
 * Winner or loser
 */
public class WinnerWindow{

	/*
	 * displays all the user interface and actions
	 */
	public static void display(String outcome, String bgId){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // do this first, than go back to other screen
		window.initStyle(StageStyle.UNDECORATED); // no top bar

		//new border pane
		BorderPane bp = new BorderPane();
		bp.setId(bgId);
		VBox box = new VBox(30);
		bp.setPadding(new Insets(10, 10, 40, 10));

		// adding the uno image view to the borderpane
		File file = new File("bin/unoLogo.png");
		Image unoLogo = new Image(file.toURI().toString());
		ImageView iv = new ImageView(unoLogo);
		BorderPane.setAlignment(iv, Pos.CENTER);
		iv.setFitHeight(150);
		iv.setFitWidth(175);
		bp.setTop(iv);

		// new label that shows the outcome of the game
		Label lbl = new Label(outcome);
		lbl.setId("winnerWindowLbl");


		//main button, going back to main menu
		Button mainButton = new Button("Main Menu");
		mainButton.setId("mainMenuButtonsSmaller");
		mainButton.setMinHeight(50);
		mainButton.setMinWidth(200);


		// adding all children to the vbox
		box.getChildren().addAll(lbl, mainButton);
		box.setAlignment(Pos.CENTER); // setting the alignment
		bp.setCenter(box);

		// setting the scene
		Scene scene = new Scene(bp, 400, 400);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);

		mainButton.setOnAction(e ->{
			MainMenu.window.setScene(MainMenu.getScene());
			window.close();
		});
		
		// cannot exit the screen
		window.setOnCloseRequest(e -> {
			e.consume();
		});

		window.showAndWait();
	}

}