package application.scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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
    
    
    
    @FXML
    private ChoiceBox<String> choiceBox_lecture = new ChoiceBox<String>();
    

    

    public void initialize() {
    	setUpChoiceBox();
    	//add listener to choicebox to alert user theyre changing lecture topic
    	choiceBox_lecture.getSelectionModel()
        .selectedItemProperty()
        .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> makeViewAlert() );
    }
    
    @FXML
    void clearListButtonPressed(ActionEvent event) {
    	clearList();
    }
    
    @FXML
    void clearList() {
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
    
    
	/** View handler for viewing topic notes */
	@FXML
	private void viewNotesFromFile(ActionEvent event) {
		makeViewAlert();
	}
	
	@FXML
    void makeViewAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		// Customising dialog
		alert.setTitle("View Notes");
		alert.setHeaderText("Confirmation to view Notes");
		alert.setContentText("Your current unsaved notes will be replaced.\nWould you like to Continue?\n");

		// Changing the text of the alert buttons
		ButtonType yesBtn = new ButtonType("Replace current notes", ButtonData.OK_DONE);
		ButtonType noBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(noBtn, yesBtn);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == yesBtn) {
			clearList();
			view();
		}
    	
    }
	
	
    @FXML
    void view() {
    	String topic = choiceBox_lecture.getValue();
    	textField_lectureName.setText(topic);
    	String path = "./src/application/scene/LectureNotesForTopic/"+topic+".txt";
    	 try (BufferedReader br = new BufferedReader(new FileReader(path))) {
     
                // Declaring a new string
                String str;
                String date = "";
                // It holds true till there is content in file
                while ((str = br.readLine()) != null) {
                		//this is date made
                		if (str.contains("++dateMade(")) {
                			date = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                		}
                		else {
                			addNote("Note Made ("+date+"):   "+str);
                		}
                    // Printing the file data
                    
                }
            }
    	 
         catch (Exception e) {
        	 
             // Display pop up message if exceptionn occurs
        	 e.printStackTrace(System.out);
         }
    	
    }
    

    
    private void setUpChoiceBox() {
    	File folder = new File("./src/application/scene/LectureNotesForTopic/");
    	File[] listOfFiles = folder.listFiles();

    	for (int i = 0; i < listOfFiles.length; i++) {
    	  if (listOfFiles[i].isFile()) {
    	    if (!listOfFiles[i].getName().contains("LectureNotesThisFolder.txt")) {
    	    	String[] split = listOfFiles[i].getName().split(".txt");
    	    	choiceBox_lecture.getItems().add(split[0]);
    	    }
    	    
    	  }
    	}
    }

    private void appendTolectureChoicebox(String fileName) {
    	if (!choiceBox_lecture.getItems().contains(fileName)) {
    			choiceBox_lecture.getItems().add(fileName);
    	}
    }
    
    
    
    /**
	   * If a lecture topic has been chosen, save to that topics file
	   * Otherwise make a new file with current date as time
	   */
    @FXML
    void saveToFile(ActionEvent event) {

    	String fileName = textField_lectureName.getText();
    	File file;
    	boolean topic=false;
    	String currentDate= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    	
    	//create files, either named topic or current date
    	if (!fileName.equals("")) {
    		topic=true;
    		//add option to view topic later in choicebox
    		fileName = fileName.replace(" ", "_");
    		appendTolectureChoicebox(fileName);
    		
    		file = new File("./src/application/scene/LectureNotesForTopic/" + fileName + ".txt");
    	}
    	else {
    		fileName = currentDate;
    		file = new File("./src/application/scene/LectureNotes/" + fileName + ".txt");
    	}
    	
    	
        try {
            FileWriter attributeWriter = new FileWriter(file, true);
            if (topic) {
            	String madeDate= new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
            	attributeWriter.write("++dateMade("+madeDate+")");
				attributeWriter.write(System.getProperty( "line.separator" ));
            }
            
			for (String element : listView_LECTURE_NOTES.getItems()) {
				//dont save notes that are already in the file
				if (!(element.contains("Made ("))) {
					attributeWriter.write(element);
					attributeWriter.write(System.getProperty( "line.separator" ));
				}

				
				
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
    void addNoteFromUser(ActionEvent event) {
    	String noteMade = textView_USERS_COMMENTS.getText();
    	
    	addNote(noteMade);
    	
    }
    
    private void addNote(String note) {
    	listView_LECTURE_NOTES.getItems().add(note);
    }
    

    @FXML
    void timer(ActionEvent event) {
    	SceneManager.switchToTimerScene();
    	saveToFile(null);
    }

}
