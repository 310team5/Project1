package application.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Dashboard {
	  @FXML private Button buttonDashboard;
	  @FXML private Button buttonNotes;
	  @FXML private Button buttonTodo;
	  @FXML private Button buttonDrawboard;
	  @FXML private Button buttonTimer;
	  @FXML private Button buttonMainNotes;
	  @FXML private Button buttonMainTodo;
	  @FXML private Button buttonMainDrawboard;
	  @FXML private Button buttonMainTimer;
	  @FXML private Button buttonTips;
	  
	  /** Click handler for the notes button. */
	  @FXML
	  private void notes() {
	    SceneManager.switchToNotesScene();
	  }
	  
	  /** Click handler for the ToDo button. */
	  @FXML
	  private void todo() {
	    SceneManager.switchToTodoScene();
	  }
	  
	  /** Click handler for the drawboard button. */
	  @FXML
	  private void drawboard() {
	    SceneManager.switchToDrawboardScene();
	  }
	  
	  /** Click handler for the Timer button. */
	  @FXML
	  private void timer() {
	    SceneManager.switchToTimerScene();
	  }
	  
	  /** Click handler for the tips button. */
	  @FXML
	  private void tips() {
	    SceneManager.switchToTipsScene();
	  }
	  
}
