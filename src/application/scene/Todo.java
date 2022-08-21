package application.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

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

    Alert alert_noContentWarning = new Alert(Alert.AlertType.WARNING);

    public void initialize() {
        datePicker.setValue(LocalDate.now());

        //Add TODO DOING DONE status to the choiceBox dropdown
        choiceBox_status.getItems().addAll("TODO", "DOING", "DONE");
        choiceBox_status.setValue("TODO");


    }

    @FXML
    private void addEvent() {
        String status = choiceBox_status.getValue();

        if (textField_eventName.getText().isEmpty()) {
            noContentAlert();
        } else {
            switch (status) {
                case "TODO":
                    listView_TODO.getItems().add("At " + datePicker.getValue() + " " + textField_eventName.getText());
                    break;
                case "DOING":
                    listView_DOING.getItems().add("123");
                    break;
                case "DONE":
                    listView_DONE.getItems().add("123");
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    private void moveToDOING() {
        String currentItem = listView_TODO.getSelectionModel().getSelectedItem();
        if (!currentItem.isEmpty()) {
            listView_DOING.getItems().add(currentItem);
            listView_TODO.getItems().remove(currentItem);

        }
    }

    @FXML
    private void moveToDONE() {
        String currentItem = listView_DOING.getSelectionModel().getSelectedItem();
        if (!currentItem.isEmpty()) {
            listView_DONE.getItems().add(currentItem);
            listView_DOING.getItems().remove(currentItem);

        }
    }

    public void noContentAlert() {
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
}
