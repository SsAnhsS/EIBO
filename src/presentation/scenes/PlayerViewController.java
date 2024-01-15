package presentation.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import application.MP3_App;
import application.ViewName;
import business.MP3Player;
import business.Track;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;

/**Player View Controller Klasse
 * Player View kontrollieren
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class PlayerViewController {
	
	private MP3_App app;
	private MP3Player player;

	PlayerView playerView;
	
	PlaylistViewController playlistViewController;
	
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
	Duration duration;
	
	Slider volumeSlider;
	Text volume;
	
	boolean isPlaying;
	
	public PlayerViewController(MP3_App app, MP3Player player) {
		this.player = player;
		this.app = app;
		
		playerView = new PlayerView();
		
		playlistViewController = new PlaylistViewController(app, player);
		
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
		
		isPlaying = player.isPlaying;
		
		initialize();
	}
	
	private void initialize() {
		
		//Kontrolle des Play Button
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				new Thread() {
					
					public void run() {
						
						if(isPlaying) {
							isPlaying = false;
							Platform.runLater(() -> {
								playButton.getStyleClass().remove("pause-icon");
								playButton.getStyleClass().add("play-icon");
							});
			                player.pause();
						} else {
							isPlaying = true;
				            Platform.runLater(() -> {
				            	playButton.getStyleClass().remove("play-icon");
				            	playButton.getStyleClass().add("pause-icon");
				            });
			                player.play();
						}
						
					}
					
				}.start();
				
			}
		});
		
		//Kontrolle des Skip Button
		skipButton.setOnAction(event ->{
			player.skip();
			setInfo(player.track);
		});
		
		//Kontrolle des Skip-Back Button
		skipbackButton.setOnAction(event ->{
			player.skipback();
			setInfo(player.track);
		});
		
		//Kontrolle des Shuffle Button
		shuffleButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				player.shuffle();
				playlistViewController.updatePlaylist();
			}
		});
		
		//Kontrolle des Repeat Button
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
		
//		musicProgress.valueProperty().addListener(new ChangeListener<Duration>() {
//			public void changed(ObservableValue<? extends Duration> observable, Duration oldTime, Duration newTime) {
//				
//				
//			}
//		});
		
		//Änderung der Volume
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				player.volume(newValue.intValue());
				volume.setText(newValue.intValue() + "");
			}
		});
		
		//Änderung der Player View zu Playlist View
		playlistButton.setOnAction(event -> {
			app.switchView(ViewName.PlaylistView);
		});
	}
	
	/**
	 * Informationen von Track in Player View einsetzen
	 * @param aktTrack
	 */
	public void setInfo(Track aktTrack) {
		songName.setText(aktTrack.getTitle());
		artistName.setText(aktTrack.getArtist());
		albumName.setText(aktTrack.getAlbumTitle());
		
		try {
			imageView.setImage(new Image(new FileInputStream(aktTrack.getPhotoCover())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		musicProgress.setProgress(0.0);
		
		time.setText(aktTrack.getLength()/60 + ":" + aktTrack.getLength()%60);
		
		duration = Duration.seconds(aktTrack.getLength());
		timeline = new Timeline(
				new KeyFrame(Duration.ZERO, event -> musicProgress.setProgress(0), null),
				new KeyFrame(duration, event -> musicProgress.setProgress(1), null)
		);
		
		timeline.setCycleCount(1);
	}
	
	public Pane getRoot() {
		return playerView;
	}
	
}
