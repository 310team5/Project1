package application.scene;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneManager {
	  private static Stage stage;

	  /**
	   * Show the main menu scene to the user. This must be called before any switch... methods.
	   *
	   * @param stage The JavaFX stage to use.
	   */
	  public static void show(Stage stage) {
	    SceneManager.stage = stage;

	    // Load empty to allow scene switch.
	    stage.setScene(new Scene(new Group()));

	    // Show dashboard scene.
	    switchToDashboardScene();
	    stage.show();
	  }

	  /** Show the dashboard scene to the user. */
	  public static void switchToDashboardScene() {
		  changeScene("Dashboard");
	  }
	  
	  /** Show the notes scene to the user. */
	  public static void switchToNotesScene() {
		  changeScene("Notes");
	  }
	  
	  /** Show the todo scene to the user. */
	  public static void switchToTodoScene() {
		  changeScene("Todo");
	  }

	  /** Show the drawboard scene to the user. */
	  public static void switchToDrawboardScene() {
		  changeScene("Drawboard");
	  }
	  
	  /** Show the timer scene to the user. */
	  public static void switchToTimerScene() {
		  popOut("TimerPopOut");
	  }

	  /**
	   * Changes the currently displayed scene to the user.
	   *
	   * @param name The name of the FXML and CSS files without the file extension.
	   * @return The FXMLLoader to allow access to the controller instance.
	   */
	  private static FXMLLoader popOut(String name) {
	    try {
	      // Load resources.
	      FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("fxml/" + name + ".fxml"));
	      Parent root = loader.load();
	      String css = SceneManager.class.getResource("css/" + name + ".css").toExternalForm();
	      // Show new scene.
	      Scene scene = new Scene(root);
	      Stage stage = new Stage();
	      stage.setScene(scene);
	      stage.setHeight(200);
	      stage.setWidth(400);
//	      stage.setHeight(1000);
//	      stage.setWidth(1000);
	      stage.setAlwaysOnTop(true);
	      stage.setResizable(false);
		  stage.setTitle("Pomodoro"); 
		 
	      stage.show();
	      //Scene scene = stage.getScene();
	      //scene.setRoot(root);

	      // Apply CSS.
	      scene.getStylesheets().clear();
	      scene.getStylesheets().add(css);

	      return loader;
	    } catch (IOException e) {
	      alert("Could not load FXML or CSS resources.");
	      return null;
	    }
	  }

	  /**
	   * Changes the currently displayed scene to the user.
	   *
	   * @param name The name of the FXML and CSS files without the file extension.
	   * @return The FXMLLoader to allow access to the controller instance.
	   */
	  private static FXMLLoader changeScene(String name) {
	    try {
	      // Load resources.
	      FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("fxml/" + name + ".fxml"));
	      Parent root = loader.load();
	      String css = SceneManager.class.getResource("css/" + name + ".css").toExternalForm();
	      // Show new scene.
	      Scene scene = stage.getScene();
	      scene.setRoot(root);

	      // Apply CSS.
	      scene.getStylesheets().clear();
	      scene.getStylesheets().add(css);

	      return loader;
	    } catch (IOException e) {
	      alert("Could not load FXML or CSS resources.");
	      return null;
	    }
	  }

	  /**
	   * Show an alert dialog box to the user.
	   *
	   * @param message The message to show in the dialog box.
	   */
	  public static void alert(String message) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(message);
	    alert.showAndWait();
	  }

	  /** Closes the JavaFX window. */
	  public static void closeWindow() {
	    stage.close();
	  }
}
