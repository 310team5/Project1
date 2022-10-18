package application.scene;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LectureView {
	
	public MediaPlayer mediaPlayer;
	public boolean playing;
    @FXML
    private Slider timeSlider;
    
    @FXML
    private Button fastForward;

    @FXML
    private MediaView lecturePlayer;

    @FXML
    private Button playButton;

    @FXML
    private Button rewind;

    @FXML
    private Slider volumeSlider;

    // Both seek functions will seek 10 seconds forward, or back
    @FXML
    void fastForwardVideo(MouseEvent event) {
    	mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() + 10));
    }

    @FXML
    void rewindVideo(MouseEvent event) {
    	mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() - 10));
    }
    
    // Play or pause the media file depending on its current state
    @FXML
    void playOrPause(MouseEvent event) {
    	if (playing) {
    		mediaPlayer.pause();
    		playing = false;
    		playButton.setText("Play");
    	} else {
    		mediaPlayer.play();
    		playing = true;
    		playButton.setText("Pause");
    	}
    }
	
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
	    mediaPlayer = new MediaPlayer(videoMedia);
	    mediaPlayer.setAutoPlay(true);
	    
	    // Linking volume slider to media volume
	    mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(100));
		
	    // Media JFX Field
		lecturePlayer.setMediaPlayer(mediaPlayer);
		playing = true;

	}
	
}
