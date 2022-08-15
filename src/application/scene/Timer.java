package application.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class Timer {
	  @FXML private Button button_dashboard;

	  @FXML private ToggleButton pomodoroButton, shortBreakButton, longBreakButton;

	  @FXML private Text countdownText;

	  @FXML
	  public void initialize(){
		  pomodoroButton.fire();
		  countdownText.setText("25:00");
	  }

	  /** Click handler for the dashboard button. */
	  @FXML
	  private void dashboard() {
	    SceneManager.switchToDashboardScene();
	  }

	  @FXML
	  public void selectTimerButton(ActionEvent event){
		   if (pomodoroButton.isSelected()){
			   pomodoroButton.setDisable(true);
			   pomodoroButton.setStyle("-fx-opacity: 1.0;");

			   shortBreakButton.setDisable(false);
			   longBreakButton.setDisable(false);

			   countdownText.setText("25:00");

		   } else if (shortBreakButton.isSelected()){
			   shortBreakButton.setDisable(true);
			   shortBreakButton.setStyle("-fx-opacity: 1.0;");

			   pomodoroButton.setDisable(false);
			   longBreakButton.setDisable(false);

			   countdownText.setText("05:00");

		   } else if (longBreakButton.isSelected()){
			   longBreakButton.setDisable(true);
			   longBreakButton.setStyle("-fx-opacity: 1.0;");

			   pomodoroButton.setDisable(false);
			   shortBreakButton.setDisable(false);

			   countdownText.setText("15:00");
		   }
	  }
}

