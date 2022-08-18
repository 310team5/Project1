package application.scene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer{

	  @FXML private ToggleButton pomodoroButton, shortBreakButton, longBreakButton;
	  @FXML private Text countdownText;
	  @FXML private Button startButton;
	  private static final String POMODORO_TIME = "25:00";
	  private static final String SHORT_BREAK_TIME = "05:00";
	  private static final String LONG_BREAK_TIME = "15:00";
	  private static final String DISABLE_BUTTON_STYLE = "-fx-opacity: 1.0;";
	  private Time time = new Time();
	  private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
		  if (time.getMinutes()==0 && time.getSeconds()==0){
			  stopTimer();
		  } else{
			  time.secondCountDown();
			  countdownText.setText(time.getCurrentTime());
		  }
	  }));

	  @FXML
	  public void initialize(){
		  pomodoroButton.fire();
		  countdownText.setText(POMODORO_TIME);
		  time.setCurrentTime(POMODORO_TIME);

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
			   pomodoroButton.setStyle(DISABLE_BUTTON_STYLE);

			   shortBreakButton.setDisable(false);
			   longBreakButton.setDisable(false);

			   countdownText.setText(POMODORO_TIME);
			   time.setCurrentTime(POMODORO_TIME);
			   stopTimer();

		   } else if (shortBreakButton.isSelected()){
			   shortBreakButton.setDisable(true);
			   shortBreakButton.setStyle(DISABLE_BUTTON_STYLE);

			   pomodoroButton.setDisable(false);
			   longBreakButton.setDisable(false);

			   countdownText.setText(SHORT_BREAK_TIME);
			   time.setCurrentTime(SHORT_BREAK_TIME);
			   stopTimer();

		   } else if (longBreakButton.isSelected()){
			   longBreakButton.setDisable(true);
			   longBreakButton.setStyle(DISABLE_BUTTON_STYLE);

			   pomodoroButton.setDisable(false);
			   shortBreakButton.setDisable(false);

			   countdownText.setText(LONG_BREAK_TIME);
			   time.setCurrentTime(LONG_BREAK_TIME);
			   stopTimer();

		   }
	  }

	  @FXML
	  public void controlTimer(ActionEvent event){
		  String timerStatus = startButton.getText();
		  if (timerStatus.equals("START")){
			  startTimer();
		  } else if (timerStatus.equals("STOP")) {
			  stopTimer();
		  }
	  }

	  public void startTimer(){
		  timeline.setCycleCount(Timeline.INDEFINITE);
		  timeline.play();
		  startButton.setText("STOP");
	  }

	  public void stopTimer(){
		  timeline.pause();
		  startButton.setText("START");
	  }
}

