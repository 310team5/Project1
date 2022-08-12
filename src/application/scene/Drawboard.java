package application.scene;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Drawboard {
	
	@FXML private ColorPicker colorpicker;
	@FXML private Canvas canvas;
	@FXML private ChoiceBox<String> brush_size;
	@FXML private Button button_dashboard;
	@FXML private ToggleButton button_brush;
	@FXML private ToggleButton button_eraser;
	@FXML private Button button_save;
	@FXML private TextField text_field_title;
	@FXML private Text text_save_status;
	@FXML private Slider slider_brush_size;
	GraphicsContext brush_tool;
	// add a toggle group
	
	public void initialize() {

		brush_tool =  canvas.getGraphicsContext2D();
			
		ToggleGroup tool_group = new ToggleGroup();
		button_brush.setToggleGroup(tool_group);
		button_eraser.setToggleGroup(tool_group);
		
		// tools setup
		canvas.setOnMouseDragged( e -> {
			double size = slider_brush_size.getValue();		
			double x = e.getX() - size / 2;
			double y = e.getY() - size / 2;
			
			if (tool_group.getSelectedToggle() == button_brush) {
				brush_tool.setFill(colorpicker.getValue());
				brush_tool.fillRoundRect(x, y, size, size, size, size);
			}
			if (tool_group.getSelectedToggle() == button_eraser) {
				brush_tool.setFill(Color.WHITE);
				brush_tool.fillRoundRect(x, y, size, size, size, size);
			}
			
		});
		
	}
	
	/** Click handler for the save drawing button. */
	@FXML
	private void save() {
		String save_message;
		
		try {
			String drawing_title = text_field_title.getText();
			drawing_title = drawing_title.replace(" ", "_");
			
			 Image snapshot = canvas.snapshot(null, null); 
			 ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("./drawings/" + drawing_title + ".png"));
			 
			 save_message = "Your drawing was saved!";
		} catch (Exception e) {
			System.out.println("Failed to save image: " + e);
			save_message = "There was an error saving your drawing. Please try again";
		}
		
		text_save_status.setText(save_message);

	}
	
	/** Click handler for the dashboard button. */
	@FXML
	private void dashboard() {
	SceneManager.switchToDashboardScene();
	}
}
