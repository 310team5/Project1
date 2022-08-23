package application.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

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


    public void initialize() {

        textField_eventName.setPromptText("TODO Events");

        datePicker.setValue(LocalDate.now());

        choiceBox_status.getItems().addAll("TODO", "DOING", "DONE");
        choiceBox_status.setValue("TODO");

        getHistory(listView_TODO, "TODO");
        getHistory(listView_DOING, "DOING");
        getHistory(listView_DONE, "DONE");


    }

    @FXML
    private void addEvent() {
        String status = choiceBox_status.getValue();
        String eventMessage = datePicker.getValue() + " " + textField_eventName.getText();

        if (textField_eventName.getText().isEmpty()) {
            noContentAlert();
        } else {
            switch (status) {
                case "TODO":
                    addToList(listView_TODO, "TODO", eventMessage);
                    break;
                case "DOING":
                    addToList(listView_DOING, "TODO", eventMessage);
                    break;
                case "DONE":
                    addToList(listView_DONE, "TODO", eventMessage);
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

                rewriteHistory(listView_TODO, "TODO");
                rewriteHistory(listView_DOING, "DOING");
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

                rewriteHistory(listView_DOING, "DOING");
                rewriteHistory(listView_DONE, "DONE");
            }
        } catch (Exception e) {
        }

    }

    @FXML
    private void clearDONE() {
        Alert alert_clearDONEConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to remove all Events in this area", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert_clearDONEConfirmation.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.YES.equals(result)) {
            listView_DONE.getItems().clear();
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
        alert_noContentWarning.setContentText("Please Fill Event Name");

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

    /**
     * Click handler for the drawboard button.
     */
    @FXML
    private void drawboard() {
        SceneManager.switchToDrawboardScene();
    }
}
