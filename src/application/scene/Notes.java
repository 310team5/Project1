package application.scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Notes {

    @FXML
    private Button button_DELETE_ALL;

    @FXML
    private Button button_SAVE_ALL_TO_FILE;

    @FXML
    private Button button_SAVE_NOTE;

    @FXML
    private Button button_dashboard;
    
    @FXML
    private Button button_todo;

    @FXML
    private ListView<String> listView_LECTURE_NOTES;

    @FXML
    private TextArea textView_USERS_COMMENTS;
    
    @FXML
    private Text fileNameStored;

    @FXML
    void clearList(ActionEvent event) {
    	listView_LECTURE_NOTES.getItems().clear();
    }
    
    @FXML
    void todo(ActionEvent event) {
    	SceneManager.switchToTodoScene();
    	saveToFile(null);
    }

    @FXML
    void dashboard(ActionEvent event) {
    	SceneManager.switchToDashboardScene();
    	saveToFile(null);
    }

    @FXML
    void drawboard(ActionEvent event) {
    	SceneManager.switchToDrawboardScene();
    	saveToFile(null);
    }

    @FXML
    void notes(ActionEvent event) {
    	SceneManager.switchToNotesScene();
    	saveToFile(null);
    }

    @FXML
    void saveToFile(ActionEvent event) {
        String fileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        File file = new File("./src/application/scene/LectureNotes/" + fileName + ".txt");

        try {
            FileWriter attributeWriter = new FileWriter(file, true);
			for (String element : listView_LECTURE_NOTES.getItems()) {
				attributeWriter.write(element);
				attributeWriter.write(System.getProperty( "line.separator" ));
			}
			attributeWriter.flush();  
			attributeWriter.close(); 
			fileNameStored.setText("File saved to src/application/scene/LectureNotes/ Under the File Name " + fileName + ".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void saveToList(ActionEvent event) {
    	String noteMade = textView_USERS_COMMENTS.getText();
    	listView_LECTURE_NOTES.getItems().add(noteMade);
    }

    @FXML
    void timer(ActionEvent event) {
    	SceneManager.switchToTimerScene();
    	saveToFile(null);
    }

}
