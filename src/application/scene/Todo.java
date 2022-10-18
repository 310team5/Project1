package application.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Todo {
    @FXML
    private Button button_dashboard;
    @FXML
    private Button button_addEvent;
    @FXML
    private Button button_moveDOING;
    @FXML
    private Button button_moveDONE;
    @FXML
    private Button button_clearDONE;
    @FXML
    private TextField textField_eventName;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> listView_TODO;
    @FXML
    private ListView<String> listView_DOING;
    @FXML
    private ListView<String> listView_DONE;
    @FXML
    private ChoiceBox<String> choiceBox_status;
    @FXML
    private Button button_todo;
    @FXML
    private ChoiceBox<String> choiceBox_type;
    
    private static final String NOT_STARTED_TEXT = "TODO";
    private static final String IN_PROGRESS_TEXT = "DOING";
    private static final String COMPLETED_TEXT = "DONE";
    
    private static final String STUDY_TEXT = "Study Session";
    private static final String EXAM_TEXT = "Exam";
    private static final String ASSIGNMENT_TEXT = "Assignment";
    private static final String MEETING_TEXT = "Meeting";
    private static final String OTHER_TEXT = "Other";

    public void initialize() {

        textField_eventName.setPromptText("Add a new task");

        datePicker.setValue(LocalDate.now());

        choiceBox_status.getItems().addAll(NOT_STARTED_TEXT , IN_PROGRESS_TEXT, COMPLETED_TEXT);
        choiceBox_type.getItems().addAll(STUDY_TEXT,EXAM_TEXT,ASSIGNMENT_TEXT,MEETING_TEXT,OTHER_TEXT);
        choiceBox_status.setValue(NOT_STARTED_TEXT );
        choiceBox_type.setAccessibleText("Task type");

        getHistory(listView_TODO, NOT_STARTED_TEXT );
        getHistory(listView_DOING, IN_PROGRESS_TEXT);
        getHistory(listView_DONE, COMPLETED_TEXT);

    }

    @FXML
    private void addEvent() {
        String status = choiceBox_status.getValue();
        String type = choiceBox_type.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM",Locale.getDefault());
        String formattedDate = (datePicker.getValue()).format(formatter);
        
        String eventMessage = formattedDate + " � " + type + " � " + textField_eventName.getText();

        if (textField_eventName.getText().isEmpty() || choiceBox_type.getValue() == null) {
            noContentAlert();
        } else {
            switch (status) {
                case NOT_STARTED_TEXT :
                    addToList(listView_TODO, NOT_STARTED_TEXT , eventMessage);
                    break;
                case IN_PROGRESS_TEXT:
                    addToList(listView_DOING, NOT_STARTED_TEXT , eventMessage);
                    break;
                case COMPLETED_TEXT:
                    addToList(listView_DONE, NOT_STARTED_TEXT , eventMessage);
                    break;
                default:
                    break;
            }
        }
    }


    @FXML
    private void moveToDOING() {
        String currentItem = listView_TODO.getSelectionModel().getSelectedItem();

        try {
            if (!currentItem.isEmpty()) {
                listView_DOING.getItems().add(currentItem);
                listView_TODO.getItems().remove(currentItem);

                rewriteHistory(listView_TODO, NOT_STARTED_TEXT );
                rewriteHistory(listView_DOING, IN_PROGRESS_TEXT);
            }
        } catch (Exception e) {

        }

    }

    @FXML
    private void moveToDONE() {
        String currentItem = listView_DOING.getSelectionModel().getSelectedItem();

        try {
            if (!currentItem.isEmpty()) {
                listView_DONE.getItems().add(currentItem);
                listView_DOING.getItems().remove(currentItem);

                rewriteHistory(listView_DOING, IN_PROGRESS_TEXT);
                rewriteHistory(listView_DONE, COMPLETED_TEXT);
            }
        } catch (Exception e) {
        }

    }
    
    //Clears entire list
    @FXML
    private void clearDONE() {
        Alert alert_clearDONEConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to clear your to-do list?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert_clearDONEConfirmation.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.YES.equals(result)) {
            listView_DONE.getItems().clear();
            listView_DOING.getItems().clear();
            listView_TODO.getItems().clear();
        }

        try {
            File file = new File("./src/application/scene/ToDoLists/DONE.txt");
            FileWriter clearWriter = new FileWriter(file);
            clearWriter.write("");
            clearWriter.close();
        } catch (IOException e) {
        }

    }

    private void addToList(ListView<String> listView, String status, String eventMessage) {
        try {

            File file = new File("./src/application/scene/ToDoLists/" + status + ".txt");
            FileWriter attributeWriter = new FileWriter(file, true);

            listView.getItems().add(eventMessage);
            attributeWriter.write(eventMessage + "\n");//appends the string to the file
            attributeWriter.close();
        } catch (IOException e) {

        }

    }


    private void getHistory(ListView<String> listName, String status) {
        try {
            String file = "./src/application/scene/ToDoLists/" + status + ".txt";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while (((line = br.readLine()) != null)) {
                listName.getItems().add(line);
            }
            br.close();

        } catch (IOException ex) {
        }
    }

    private void rewriteHistory(ListView<String> listName, String status) {
        try {
            File file = new File("./src/application/scene/ToDoLists/" + status + ".txt");
            FileWriter clearWriter = new FileWriter(file); //the true will append the new data
            clearWriter.write("");
            clearWriter.close();

            FileWriter attributeWriter = new FileWriter(file, true);
            List<String> history = listName.getItems();
            for (String thisAttribute : history) {
                attributeWriter.write(thisAttribute + "\n");//appends the string to the file

            }
            attributeWriter.close();
        } catch (IOException e) {
        }
    }


    public void noContentAlert() {
        Alert alert_noContentWarning = new Alert(Alert.AlertType.WARNING);
        // set content text
        alert_noContentWarning.setContentText("Please Fill Event Name and Event Type");

        // show the dialog
        alert_noContentWarning.show();
    }

    /**
     * Click handler for the dashboard button.
     */
    @FXML
    private void dashboard() {
        SceneManager.switchToDashboardScene();
    }

    /**
     * Click handler for the notes button.
     */
    @FXML
    private void notes() {
        SceneManager.switchToNotesScene();
    }

    /**
     * Click handler for the timer button.
     */
    @FXML
    private void timer() {
        SceneManager.switchToTimerScene();
    }
    
    @FXML
    private void todo() {
    	SceneManager.switchToTodoScene();
    }

    /**
     * Click handler for the drawboard button.
     */
    @FXML
    private void drawboard() {
        SceneManager.switchToDrawboardScene();
    }
}
