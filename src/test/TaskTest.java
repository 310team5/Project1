package test;

import application.scene.Todo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.io.*;
import java.time.LocalDate;

/*This tests whether a new task is created successfully from an input into a text field.
 * Requires the method in Todo to be public before it will run
 */

public class TaskTest {

	@Test
	public void test() {
		ChoiceBox<String> choiceBox_status = new ChoiceBox<String>();
		DatePicker datePicker = new DatePicker();
		TextField textField_eventName = new TextField();
		choiceBox_status.setValue("TODO");
		datePicker.setValue(LocalDate.now());
		textField_eventName.setText("Testing my todo item");
		//application.scene.Todo.addEvent();
		//Get output from text file
		String eventMessage = "";
		assertEquals(eventMessage, datePicker.getValue() + " " + textField_eventName);
	}

}
