package application;
	
import application.scene.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
	    launch(args);
	  }

	  @Override
	  public void start(Stage stage) {
	    // Set window options.
		stage.setResizable(false);
	    stage.setTitle("My Study"); //just a filler name

	    SceneManager.show(stage);
	  }
}
