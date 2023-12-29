package presentation.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.MP3_App;
import application.ViewName;
import business.MP3Player;
import business.Track;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PlayerViewController {

	PlayerView playerView;
	
	Label songName;
	Label artistName;
	Label albumName;
	
	ImageView imageView;
	
	Button playlistButton;
	
	Button playButton;
	Button skipButton;
	Button skipbackButton;
	Button shuffleButton;
	Button repeatButton;
	
	ProgressBar musicProgress;
	Text time;
	Timeline timeline;
	double DURATION_SECONDS;
	
	Slider volumeSlider;
	Text volume;
	
	private MP3_App app;
	private MP3Player player;
	
	boolean isPlaying;
	
	public PlayerViewController(MP3_App app, MP3Player player) {
		this.player = player;
		this.app = app;
		
		playerView = new PlayerView();
		
		songName = playerView.songName;
		artistName = playerView.artistName;
		albumName = playerView.albumName;
		
		imageView = playerView.imageView;
		
		playlistButton = playerView.playlistButton;
		
		playButton = playerView.playButton;
		skipButton = playerView.skipButton;
		skipbackButton = playerView.skipbackButton;
		shuffleButton = playerView.shuffleButton;
		repeatButton = playerView.repeatButton;
		
		musicProgress = playerView.musicProgress;
		time = playerView.time;
		volumeSlider = playerView.volumeSlider;
		volume = playerView.volume;
		
		setInfo(player.track);
		
		initialize();
	}
	
	public void setInfo(Track aktTrack) {
		//elapsedTime = (int) (endTime - startTime) / 1000;
		songName.setText(aktTrack.getTitle());
		artistName.setText(aktTrack.getArtist());
		albumName.setText(aktTrack.getAlbumTitle());
		
		try {
			imageView.setImage(new Image(new FileInputStream(aktTrack.getPhotoCover())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DURATION_SECONDS = aktTrack.getLength();
		time.setText(aktTrack.getLength()/60 + ":" + aktTrack.getLength()%60);
	}
	
//	private void playMusic() {
//        // Reset progress bar and timeline
//        musicProgress.setProgress(0.0);
//        
//        if (timeline != null) {
//            timeline.stop();
//        }
//
//        // Create a timeline to update the progress bar
//        timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, e -> musicProgress.setProgress(0)),
//                new KeyFrame(Duration.seconds(DURATION_SECONDS), e -> musicProgress.setProgress(1))
//        );
//        timeline.setCycleCount(1); // Play only once
//
//        // Play the timeline
//        timeline.play();
//    }
	
	private void initialize() {
		
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				new Thread() {
					
					public void run() {
						if(isPlaying) {
							isPlaying = false;
							Platform.runLater(() -> updatePlayButtonStyle());
			                player.pause();
						} else {
							isPlaying = true;
							Platform.runLater(() -> updatePlayButtonStyle());
			                player.play();
						}
						
					}
				}.start();
				
			}
		});
		
		//set isSkipped = false, when true -> change the status -> else is same
		skipButton.setOnAction(event ->{
			
			player.skip();
			isPlaying = true;
			updatePlayButtonStyle();
			setInfo(player.track);
		});
		
		skipbackButton.setOnAction(event ->{
			player.skipback();
			isPlaying = true;
			updatePlayButtonStyle();
			setInfo(player.track);
		});
		
		shuffleButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isShuffling = false;
			
			public void handle(ActionEvent event) {
				if(isShuffling) {
					shuffleButton.setOpacity(1.0);
					isShuffling = false;
				}
				else {
					shuffleButton.setOpacity(0.5);
					isShuffling = true;
				}
			}
		});
		
		repeatButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isRepeating = false;
			boolean isRepeatingOne = true;
			
			public void handle(ActionEvent event) {
				if(isRepeating) {
					repeatButton.getStyleClass().add("repeat-icon");
					repeatButton.getStyleClass().remove("repeat-one-icon");
					repeatButton.setOpacity(1);
					isRepeating = false;
					isRepeatingOne = true;
				}
				else {
					if(isRepeatingOne) {
						repeatButton.getStyleClass().add("repeat-one-icon");
						repeatButton.getStyleClass().remove("repeat-icon");
						repeatButton.setOpacity(1);
						isRepeatingOne = false;
					}
					else {
						repeatButton.getStyleClass().add("repeat-icon");
						repeatButton.getStyleClass().remove("repeat-one-icon");
						repeatButton.setOpacity(0.5);
						isRepeating = true;
					}
				}
			}
		});
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				float gainValue = newValue.intValue() - oldValue.intValue();
				player.volume(gainValue);
				volume.setText(newValue.intValue() + "");
			}
		});
		
		playlistButton.setOnAction(event -> {
			app.switchView(ViewName.PlaylistView);
		});
	}
	
	private void updatePlayButtonStyle() {
        if (isPlaying) {
            playButton.getStyleClass().add("pause-icon");
            playButton.getStyleClass().remove("play-icon");
        } else {
            playButton.getStyleClass().add("play-icon");
            playButton.getStyleClass().remove("pause-icon");
        }
    }
	
	public Pane getRoot() {
		return playerView;
	}
	
}
