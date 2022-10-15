package application.scene;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

public class Tips{

	@FXML private Button lofiBtn;
	@FXML private Button ambienceBtn;
	@FXML private Button chillBtn;
	@FXML private Button podcastBtn;
	@FXML private Button upliftingBtn;
	@FXML private ImageView cafeCard1;
	@FXML private ImageView cafeCard2;
	@FXML private ImageView cafeCard3;
	@FXML private ImageView cafeCard4;
	@FXML private ImageView cafeCard5;
	@FXML private Label cafeTitle;
	@FXML private TextArea tipsText;
	@FXML private Label tipsTitle;

	private String chillMusicLink = "https://www.youtube.com/watch?v=ziWPOQs-QCw";
	private String lofiMusicLink = "https://www.youtube.com/watch?v=jfKfPfyJRdk";
	private String podcastMusicLink = "https://www.youtube.com/watch?v=5xbADDvciko";
	private String upliftingMusicLink = "https://www.youtube.com/watch?v=pRbxlpvXw2s";
	private String ambienceMusicLink = "https://www.youtube.com/watch?v=qsOUv9EzKsg";

	@FXML
	public void initialize() throws IOException {
		hideCafe();
		hideTips();
		loadTipsContent();
	}
	
	@FXML
	public void loadTipsContent() throws IOException {
		
		tipsText.appendText("Your study tips for maximum efficency!");
		tipsText.appendText("\n");
		tipsText.appendText("\n");
		tipsText.appendText("\n");
		
		String fileDir = "./src/application/scene/TipsAssets/TipsContentText.txt";
			
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(fileDir));
			String line;
			
			while((line = in.readLine()) != null) {
				tipsText.appendText(line);
				tipsText.appendText("\n");
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			alert("There are no Tips available just yet!");
		}
				
	}
	
	public static void alert(String message) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(message);
	    alert.showAndWait();
	}
	
	@FXML
	public void setVisbilityCafe() {
		hideTips();
		if (!cafeTitle.isVisible()) {
			showCafe();
		}
	}
	
	@FXML
	public void setVisibilityTips() {
		hideCafe();
		if (!tipsText.isVisible()) {
			showTips();
		}
		
	}
	
	@FXML
	public void hideTips() {
		tipsTitle.setVisible(false);
		tipsText.setVisible(false);
	}
	
	@FXML 
	public void showTips() {
		tipsText.setVisible(true);
		tipsTitle.setVisible(true);
	}
	
	@FXML
	public void hideCafe() {
		cafeCard1.setVisible(false);
		cafeCard2.setVisible(false);
		cafeCard3.setVisible(false);
		cafeCard4.setVisible(false);
		cafeCard5.setVisible(false);
		cafeTitle.setVisible(false);
	}
	
	@FXML
	public void showCafe() {
		cafeCard1.setVisible(true);
		cafeCard2.setVisible(true);
		cafeCard3.setVisible(true);
		cafeCard4.setVisible(true);
		cafeCard5.setVisible(true);
		cafeTitle.setVisible(true);
	}
	
	
	@FXML 
	public void openLink(String link) {

		try {
			Desktop.getDesktop().browse(new URI(link));
		} catch (IOException e) {
		      alert("Sorry! This playlist is not available currently");
		} catch (URISyntaxException e) {
		      alert("Sorry! This playlist is not available currently");
		}
	}

	@FXML 
	public void openLinkLofi() {
		openLink(lofiMusicLink);
	}
	@FXML 
	public void openLinkAmbience() {
		openLink(ambienceMusicLink);
	}
	@FXML 
	public void openLinkChill() {
		openLink(chillMusicLink);
	}
	@FXML 
	public void openLinkPodcast() {
		openLink(podcastMusicLink);
	}
	@FXML 
	public void openLinkUplifting() {
		openLink(upliftingMusicLink);
	}
	
	
	/** Click handler for the dashboard button. */
	@FXML
	private void dashboard() {
		SceneManager.switchToDashboardScene();
	}

	/** Click handler for the notes button. */
	@FXML
	private void notes() {
		SceneManager.switchToNotesScene();
	}

	/** Click handler for the todo button. */
	@FXML
	private void todo() {
		SceneManager.switchToTodoScene();
	}

	/** Click handler for the drawboard button. */
	@FXML
	private void drawboard() {
		SceneManager.switchToDrawboardScene();
	}

}

