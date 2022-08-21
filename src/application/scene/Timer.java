package application.scene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer{

	  @FXML private ToggleButton pomodoroButton, shortBreakButton, longBreakButton;
	  @FXML private ToggleGroup timerButtons;
	  @FXML private Text countdownText;
	  @FXML private Button startButton;
	  private static final String POMODORO_TIME = "00:05";
	  private static final String SHORT_BREAK_TIME = "00:05";
	  private static final String LONG_BREAK_TIME = "00:05";
	  private static final String DISABLE_BUTTON_STYLE = "-fx-opacity: 1.0;";
	  private Time time = new Time();
	  private int numOfPomodoro = 0;
	  private static final int MAX_NUM_OF_POMODORO = 4;
	  private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
		  if (time.getMinutes()==0 && time.getSeconds()==0 && (shortBreakButton.isSelected() || longBreakButton.isSelected())){
			  timerButtons.selectToggle(pomodoroButton);
			  preparePomodoro();
			  stopTimer();

		  } else if (time.getMinutes()==0 && time.getSeconds()==0 && pomodoroButton.isSelected()){
			  numOfPomodoro++;

			  if (numOfPomodoro < MAX_NUM_OF_POMODORO){
				  timerButtons.selectToggle(shortBreakButton);
				  prepareShortBreak();
				  stopTimer();
			  } else if (numOfPomodoro == MAX_NUM_OF_POMODORO) {
				  numOfPomodoro = 0;

				  timerButtons.selectToggle(longBreakButton);
				  prepareLongBreak();
				  stopTimer();
			  }

		  } else {
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
			   preparePomodoro();
			   stopTimer();

		   } else if (shortBreakButton.isSelected()){
			   prepareShortBreak();
			   stopTimer();

		   } else if (longBreakButton.isSelected()){
			   prepareLongBreak();
			   stopTimer();

		   }
	  }

	  private void preparePomodoro(){
		  pomodoroButton.setDisable(true);
		  pomodoroButton.setStyle(DISABLE_BUTTON_STYLE);

		  shortBreakButton.setDisable(false);
		  longBreakButton.setDisable(false);

		  countdownText.setText(POMODORO_TIME);
		  time.setCurrentTime(POMODORO_TIME);
	  }

	  private void prepareShortBreak(){
		  shortBreakButton.setDisable(true);
		  shortBreakButton.setStyle(DISABLE_BUTTON_STYLE);

		  pomodoroButton.setDisable(false);
		  longBreakButton.setDisable(false);

		  countdownText.setText(SHORT_BREAK_TIME);
		  time.setCurrentTime(SHORT_BREAK_TIME);
	  }

	  private void prepareLongBreak(){
		  longBreakButton.setDisable(true);
		  longBreakButton.setStyle(DISABLE_BUTTON_STYLE);

		  pomodoroButton.setDisable(false);
		  shortBreakButton.setDisable(false);

		  countdownText.setText(LONG_BREAK_TIME);
		  time.setCurrentTime(LONG_BREAK_TIME);
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

