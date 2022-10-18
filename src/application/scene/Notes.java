package application.scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Button button_viewNotes;
    @FXML
    private TextField textField_lectureName;
    
    private String currentTopic;
    
    @FXML
    private ChoiceBox<String> choiceBox_lecture;
    
    

    public void initialize() {
    	setUpChoiceBox();
    }
    
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
    
    
    
//    @FXML
//    private Button button_viewNotes;
//    @FXML
//    private TextField textField_lectureName;
//    
//    @FXML
//    private ChoiceBox<String> choiceBox_lecture;
    //currentTopic
    
    private void setUpChoiceBox() {
    	File folder = new File("./src/application/scene/LectureNotesForTopic/");
    	File[] listOfFiles = folder.listFiles();

    	for (int i = 0; i < listOfFiles.length; i++) {
    	  if (listOfFiles[i].isFile()) {
    	    
    	    choiceBox_lecture.getItems().add(listOfFiles[i].getName());
    	    
    	  }
    	}
    }
    
    private void viewNotes() {
    	 String status = choiceBox_lecture.getValue();
    	 
    }
    
    @FXML
    void saveToFile(ActionEvent event) {

    	String fileName = textField_lectureName.getText();
    	File file;
    	boolean topic=false;
    	
    	if (fileName != null) {
    		topic=true;
    		fileName = textField_lectureName.getText();
    		file = new File("./src/application/scene/LectureNotesForTopic/" + fileName + ".txt");
    	}
    	else {
    		fileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    		file = new File("./src/application/scene/LectureNotes/" + fileName + ".txt");
    	}
        
        System.out.println(textField_lectureName.getText());
        
        try {
            FileWriter attributeWriter = new FileWriter(file, true);
			for (String element : listView_LECTURE_NOTES.getItems()) {
				attributeWriter.write(element);
				attributeWriter.write(System.getProperty( "line.separator" ));
				
			}
			attributeWriter.flush();  
			attributeWriter.close(); 
			if (!topic) {
				fileNameStored.setText("File saved to src/application/scene/LectureNotes/ Under the File Name " + fileName + ".txt");
			}
			else {
				fileNameStored.setText("File saved to src/application/scene/LectureNotesForTopics/ Under the File Name " + fileName + ".txt");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	}
    

    @FXML
    void addNoteToList(ActionEvent event) {
    	String noteMade = textView_USERS_COMMENTS.getText();
    	listView_LECTURE_NOTES.getItems().add(noteMade);
    }
    
    

    @FXML
    void timer(ActionEvent event) {
    	SceneManager.switchToTimerScene();
    	saveToFile(null);
    }

}
