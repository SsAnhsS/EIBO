package presentation.scenes;

import business.MP3Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PlayerViewController {

	PlayerView playerView;
	
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
	
	public PlayerViewController(MP3Player player) {
		playerView = new PlayerView();
		
		playButton = playerView.playButton;
		skipButton = playerView.skipButton;
		skipbackButton = playerView.skipbackButton;
		shuffleButton = playerView.shuffleButton;
		repeatButton = playerView.repeatButton;
		
		musicProgress = playerView.musicProgress;
		time = playerView.time;
		volumeSlider = playerView.volumeSlider;
		volume = playerView.volume;
		
		this.player = player;
		
		initialize();
	}
	
	private void initialize() {
		
		//play button is not working
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isPlaying = false;
			
			public void handle(ActionEvent event) {
				if(isPlaying) {
					//player.pause();
					isPlaying = false;
					playButton.getStyleClass().add("play");
					playButton.getStyleClass().remove("pause");
				} else {
					//player.play();
					isPlaying = true;
					playButton.getStyleClass().add("pause");
					playButton.getStyleClass().remove("play");
				}
			}
		});

		musicProgress.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				time.setText(newValue.intValue() + " / Total Time");
			}
		});
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				volume.setText(newValue.intValue() + "");
			}
		});
	}

	public Pane getRoot() {
		return playerView;
	}
	
}
