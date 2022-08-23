package application.scene;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Drawboard {
	
	@FXML private ColorPicker colorPicker;
	@FXML private Canvas canvas;
	@FXML private Button buttonDashboard;
	@FXML private Button buttonNotes;
	@FXML private Button buttonTodo;
	@FXML private Button buttonTimer;
	@FXML private ToggleButton buttonBrush;
	@FXML private ToggleButton buttonEraser;
	@FXML private Button buttonSave;
	@FXML private TextField textFieldTitle;
	@FXML private Text textToolSize;
	@FXML private Slider sliderToolSize;
	@FXML private Text toolIndicator;
	GraphicsContext brushTool;
	
	public void initialize() {

		brushTool =  canvas.getGraphicsContext2D();		
		ToggleGroup toolGroup = new ToggleGroup();
		buttonBrush.setToggleGroup(toolGroup);
		buttonEraser.setToggleGroup(toolGroup);
		
		canvas.setOnMouseDragged( e -> {
			double size = sliderToolSize.getValue();		
			double x = e.getX() - size / 2;
			double y = e.getY() - size / 2;
			
			if (toolGroup.getSelectedToggle() == buttonBrush) {
				brushTool.setFill(colorPicker.getValue());
				brushTool.fillRoundRect(x, y, size, size, size, size);
			}
			if (toolGroup.getSelectedToggle() == buttonEraser) {
				brushTool.setFill(Color.WHITE);
				brushTool.fillRoundRect(x, y, size, size, size, size);			
			}			
		});
		
		toolIndicator.setText(Integer.toString((int)sliderToolSize.getValue()) + " px");
		sliderToolSize.valueProperty().addListener(ov -> {
	            	toolIndicator.setText(Integer.toString((int)sliderToolSize.getValue()) + " px");
	        });
	}
	
	/** Click handler for the save drawing button. */
	@FXML
	private void save() {
		String saveMessage;
		
		try {
			String drawingTitle = textFieldTitle.getText();
			drawingTitle = drawingTitle.replace(" ", "_");
			
			Image snapshot = canvas.snapshot(null, null); 
			ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("./drawings/" + drawingTitle + ".png"));
			 
			 saveMessage = ("Your drawing was saved as \"" + drawingTitle + ".png" + "\"");
		} catch (Exception e) {
			System.out.println("Failed to save image: " + e);
			saveMessage = "There was an error saving your drawing. Please try again \n Tip: try not using special characters in your drawing title.";
		}
		
		DrawboardModal.openSaveModal(saveMessage);

	}
	
	/** Click handler for the dashboard button. */
	@FXML
	private void dashboard() {
		SceneManager.switchToDashboardScene();
	}
	
	/** Click handler for the notes button. */
	@FXML
	private void notes() {
		SceneManager.switchToNotesScene();
	}
	
	/** Click handler for the todo button. */
	@FXML
	private void todo() {
		SceneManager.switchToTodoScene();
	}
	
	/** Click handler for the timer button. */
	@FXML
	private void timer() {
		SceneManager.switchToTimerScene();
	}
	
}

