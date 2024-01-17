package presentation.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import application.MP3_App;
import application.ViewName;
import business.MP3Player;
import business.Playlist;
import business.Track;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
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
	
	Slider musicProgress;
	Text time;
	Timeline timeline;
	Duration duration;
	
	Slider volumeSlider;
	Text volume;
	
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
		
		setInfo(player.getTrackProperty().getValue());
		
		initialize();
	}
	
	private void initialize() {
		
		//Abspielen des aktuellen Track
		playButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				
				if(player.getPlayingProperty().getValue()) {
	                player.pause();
				} else {
	                player.play();
				}
			}
		});
		
		//Kontrolle des Skip Button
		skipButton.setOnAction(event ->{
			player.skip();
		});
		
		//Kontrolle des Skip-Back Button
		skipbackButton.setOnAction(event ->{
			player.skipback();
		});
		
		//Kontrolle des Shuffle Button
		shuffleButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isShuffling = false;
			public void handle(ActionEvent event) {
				player.shuffle();
			}
		});
		
		//Kontrolle des Repeat Button
		repeatButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			boolean isSelected = !player.getRepeat();
			
			public void handle(ActionEvent event) {
				
				if(isSelected) {
					repeatButton.setOpacity(1);
					player.setRepeat(true);
					isSelected = false;
				}
				else {
					repeatButton.setOpacity(0.5);
					player.setRepeat(false);
					isSelected = true;
				}
				
			}
		});
		
		
		
		//Listener für Play Button
		player.getPlayingProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						if (newValue) {
							playButton.getStyleClass().remove("play-icon");
			            	playButton.getStyleClass().add("pause-icon");
						} else {
							playButton.getStyleClass().remove("pause-icon");
							playButton.getStyleClass().add("play-icon");
						}
					}
				});	
				
			}
			
		});
		
		//Listener für Time Property
		player.getTimeProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						int timeValue = newValue.intValue();
						String timeText = getTimeForm(timeValue) + " / " + 
											getTimeForm(player.getTrackProperty().getValue().getLength());
						time.setText(timeText);
						musicProgress.valueProperty().set(timeValue);	
					}
				});
			}
			
		});
		
		//Listener für TrackProperty
		player.getTrackProperty().addListener(new ChangeListener<Track>() {
			//update Titel, Image Cover und Music Progress
			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldValue, Track newValue) {
				Platform.runLater(() -> setInfo(newValue));
			}
			
		});
		
		//Listener für PlaylistProperty
		player.getPlaylistProperty().addListener(new ChangeListener<Playlist>() {

			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				//Platform.runLater(() -> player.setCurrentPlaylist(newValue));
			}
			
		});
		
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
		
		time.setText("00:00 / " + getTimeForm(aktTrack.getLength()));
		musicProgress.setMax(aktTrack.getLength());
		
		//player.getTrackProperty().setValue(aktTrack);
	}
	
	/**
	 * Zeit in String
	 * @param timeValue
	 * @return
	 */
	public String getTimeForm(int timeValue) {
		String timeText = "";
		int minutes = timeValue / 60;
		int seconds = timeValue % 60;
		if(minutes < 10) {
			timeText += "0" + Integer.toString(minutes);
		}
		else {
			timeText += Integer.toString(minutes);
		}
		timeText += ":";
		if(seconds < 10) {
			timeText += "0" + Integer.toString(seconds);
		}
		else {
			timeText += Integer.toString(seconds);
		}
		return timeText;
	}
	
	public Pane getRoot() {
		return playerView;
	}
	
}
