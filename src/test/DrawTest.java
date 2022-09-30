package test;

import static org.junit.Assert.*;

import application.scene.Drawboard;

import org.junit.Test;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/*This tests whether a drawing is saved to a file
 * Needs a mock image and the save method in Drawboard.java to be public
 */

public class DrawTest {

	@Test
	public void test() {
		TextField textFieldTitle = new TextField();
		textFieldTitle.setText("testTitle");
		//application.scene.Drawboard.save();
		assertEquals(textFieldTitle,"testTitle");
		//Requires a mock drawing and then identify the saved file after the save method is called
	}

}
