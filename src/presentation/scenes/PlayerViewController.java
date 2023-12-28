package presentation.scenes;

import business.MP3Player;
import business.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PlayerViewController {

	PlayerView playerView;
	
	Label songName;
	Label artistName;
	
	Image image;
	
	Button playButton;
	Button skipButton;
	Button skipbackButton;
	Button shuffleButton;
	Button repeatButton;
	
	Slider musicProgress;
	Text time;
	Slider volumeSlider;
	Text volume;
	
	private MP3Player player;
	
	boolean isPlaying = false;
	
	long startTime;
	long endTime;
	int elapsedTime = 0;
	
	public PlayerViewController(MP3Player player) {
		playerView = new PlayerView();
		
		songName = playerView.songName;
		artistName = playerView.artistName;
		
		image = playerView.image;
		
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
		
		this.player = player;
		
		initialize();
	}
	
	public void setInfo(Track aktTrack) {
		//elapsedTime = (int) (endTime - startTime) / 1000;
		songName.setText(aktTrack.getTitle());
		artistName.setText(aktTrack.getArtist());
		//image = aktTrack.getPhotoCover();
		time.setText(elapsedTime/60 + ":" + elapsedTime%60 + " / " + aktTrack.getLength()/60 + ":" + aktTrack.getLength()%60);
	}
	
	private void initialize() {
		
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				if(isPlaying) {
					isPlaying = false;
					player.pause();
					playButton.getStyleClass().add("play-icon");
					playButton.getStyleClass().remove("pause-icon");
					
				} else {
					isPlaying = true;
					player.play();
					playButton.getStyleClass().add("pause-icon");
					playButton.getStyleClass().remove("play-icon");
				}
			}
		});
		
		skipButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				player.skip();
				isPlaying = true;
				playButton.getStyleClass().add("pause-icon");
				playButton.getStyleClass().remove("play-icon");
				setInfo(player.track);
			}
		});
		
		skipbackButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				player.skipback();
				isPlaying = true;
				playButton.getStyleClass().add("pause-icon");
				playButton.getStyleClass().remove("play-icon");
				setInfo(player.track);
			}
		});
		
		shuffleButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isShuffling = false;
			
			public void handle(ActionEvent event) {
				if(isShuffling) {
					shuffleButton.setOpacity(1.0);
					isShuffling = false;
				}
				else {
					shuffleButton.setOpacity(0.6);
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
						repeatButton.setOpacity(0.6);
						isRepeating = true;
					}
				}
			}
		});
		
		musicProgress.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				time.setText(newValue.intValue() + " / " + player.track.getLength());
			}
		});
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				float gainValue = newValue.intValue() - oldValue.intValue();
				player.volume(gainValue);
				volume.setText(newValue.intValue() + "");
			}
		});
	}

	public Pane getRoot() {
		return playerView;
	}
	
}
