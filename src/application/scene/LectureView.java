package application.scene;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LectureView {

    @FXML
    private MediaView lecturePlayer;
	
	@FXML
	public void initialize() throws IOException{
		// The default timer setting is pomodoro
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File videoFile = fileChooser.showOpenDialog(stage);
		
		String mimeType = Files.probeContentType(videoFile.toPath());
		System.out.println(mimeType);
		if (mimeType.contains("video/mp4")) {	
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not MP4");
			alert.setContentText("The file you have chosen is not an mp4, currently only mp4 files are supported");

			alert.showAndWait();
			
		    Stage currentStage = (Stage) lecturePlayer.getScene().getWindow();
		    currentStage.close();		   
			return;
		}
		
		// Media Source
		URI directory = videoFile.toURI();
		Media videoMedia = new Media(directory.toString());
		
		// Media Player
	    MediaPlayer mediaPlayer = new MediaPlayer(videoMedia);
	    mediaPlayer.setAutoPlay(true);
		
	    // Media JFX Field
		lecturePlayer.setMediaPlayer(mediaPlayer);

	}
	
}
