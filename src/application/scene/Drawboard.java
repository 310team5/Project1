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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Drawboard {
	
	@FXML private ColorPicker colorpicker;
	@FXML private Canvas canvas;
	@FXML private ChoiceBox brush_size;
	@FXML private Button button_dashboard;
	@FXML private ToggleButton button_brush;
	@FXML private ToggleButton button_eraser;
	@FXML private ToggleButton button_rectangle;
	@FXML private Button button_save;
	GraphicsContext brush_tool;
	// add a toggle group
	
	public void initialize() {
		brush_tool =  canvas.getGraphicsContext2D();
		
		brush_size.setValue("1");
		brush_size.getItems().addAll("1", "2", "6", "4", "10", "15","20");
		
		ToggleGroup tool_group = new ToggleGroup();
		button_brush.setToggleGroup(tool_group);
		button_eraser.setToggleGroup(tool_group);
		button_rectangle.setToggleGroup(tool_group);
		
		
		canvas.setOnMouseDragged( e -> {
			String temp = brush_size.getValue().toString();
			double size = Double.parseDouble(temp);
			double x = e.getX() - size / 2;
			double y = e.getY() - size / 2;
			
			if (tool_group.getSelectedToggle() == button_brush) {
				//brush_tool.setStroke(Color.BLACK);
				brush_tool.setFill(colorpicker.getValue());
				//brush_tool.setLineWidth(size);
				//brush_tool.fillRect(x, y, size, size);
				brush_tool.fillRoundRect(x, y, size, size, size, size);
			}
			if (tool_group.getSelectedToggle() == button_eraser) {
				//brush_tool.setStroke(Color.BLACK);
				brush_tool.setFill(Color.web("#f2f2f2"));
				//brush_tool.setLineWidth(size);
				//brush_tool.fillRect(x, y, size, size);
				brush_tool.fillRoundRect(x, y, size, size, size, size);
			}
			
		});
		
	}
	
	/** Click handler for the save drawing button. Incomplete */
	@FXML
	private void save() {
		try {
			Image snapshot = canvas.snapshot(null, null);
			
			ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("drawing.png"));
		} catch (Exception e) {
			System.out.println("There was an error saving your drawing: " + e);
		}
	}
	
	/** Click handler for the dashboard button. */
	@FXML
	private void dashboard() {
	SceneManager.switchToDashboardScene();
	}
}
