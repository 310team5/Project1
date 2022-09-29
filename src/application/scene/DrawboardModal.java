package application.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DrawboardModal {
    	/**
	 * Show a pop-up message to the user. Indicates whether their drawing save was successful
	 *
	 * @param message The JavaFX the message the modal displays after drawing save
	 */
	private static final int MINIMUM_WIDTH = 400;
	private static final int MINIMUM_HEIGHT = 150;
	private static final int VBOX_CHILDREN_SPACING = 10;
	
	public static void openSaveModal(String message) {
		Stage saveModal = new Stage();
		
		saveModal.initModality(Modality.APPLICATION_MODAL);
		saveModal.setTitle("save drawing");
		saveModal.setMinWidth(MINIMUM_WIDTH);
		saveModal.setMinHeight(MINIMUM_HEIGHT);
		
		Text saveMessage = new Text();
		saveMessage.setText(message);
		
		Button closeButton = new Button("Got it");
		closeButton.setOnAction(e -> saveModal.close());
		
		VBox modalLayout = new VBox(VBOX_CHILDREN_SPACING);
		modalLayout.getChildren().addAll(saveMessage, closeButton);
		modalLayout.setAlignment(Pos.CENTER);
		
		Scene modalScene = new Scene (modalLayout);
		saveModal.setScene(modalScene);
		
		saveMessage.getStyleClass().add("-fx-font-size: 20px");
		closeButton.getStyleClass().add("-fx-font-size: 20px");
		
		saveModal.showAndWait();
		
	}

}
