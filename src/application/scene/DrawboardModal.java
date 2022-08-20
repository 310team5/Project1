package application.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DrawboardModal {
    
    	/** Opens the save drawing modal in drawboard scene to the user. */
	public static  void openSaveModal(String message) {
		Stage saveModal = new Stage();
		
		saveModal.initModality(Modality.APPLICATION_MODAL);
		saveModal.setTitle("save drawing");
		saveModal.setMinWidth(400);
		saveModal.setMinHeight(150);
		
		Text saveMessage = new Text();
		saveMessage.setText(message);
		
		Button closeButton = new Button("Got it");
		closeButton.setOnAction(e -> saveModal.close());
		
		VBox modalLayout = new VBox(10);
		modalLayout.getChildren().addAll(saveMessage, closeButton);
		modalLayout.setAlignment(Pos.CENTER);
		
		Scene modalScene = new Scene (modalLayout);
		saveModal.setScene(modalScene);
		
		saveMessage.getStyleClass().add("-fx-font-size: 20px");
		closeButton.getStyleClass().add("-fx-font-size: 20px");
		
		saveModal.showAndWait();
		
	}

}
