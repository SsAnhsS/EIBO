package presentation.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlView extends TilePane{
	
	public final double DISTANCE = 10;
	
	public Button playlistButton;
	
	public Insets sameInsets = new Insets(10);
	public double sameWidth = 400;
	
	public Button playButton;
	public Button skipButton;
	public Button skipbackButton;
	
	public Slider musicProgress;
	public Text time;
	public Slider volumeSlider;
	public Text volume;
	public int defaultVolume = 50;
	
	public ControlView() {
		VBox box = new VBox();
		
		HBox centerBox = new HBox();
		setCenterBox(centerBox);
		
		HBox bottomBox = new HBox();
		setBottomBox(bottomBox);
		
		box.getChildren().addAll(centerBox, bottomBox);
		box.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(box);
	}
	

	/**
	 * Center-Box von Player View einstlellen
	 * 
	 * @param centerBox
	 */
	public void setCenterBox(HBox centerBox) {
		musicProgress = new Slider();
		musicProgress.setPrefWidth(1100);
		musicProgress.setPrefHeight(12);
		time = new Text("");
		
		centerBox.getChildren().addAll(musicProgress, time);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(DISTANCE);
	}
	
	/**
	 * Bottom-Box von Player View einstellen 
	 * Bottom-Box:
	 * 		- playlist-box
	 * 		- player-box
	 * 		- volume-box
	 * 	
	 * @param bottomBox
	 */
	public void setBottomBox(HBox bottomBox) {
		HBox playlistBox = new HBox();
		
		playlistButton = new Button ("");
		playlistButton.setId("playlist-button");
		playlistButton.getStyleClass().add("icon-button");
		playlistButton.getStyleClass().add("playlist-icon");
		
		playlistBox.getChildren().add(playlistButton);
		playlistBox.setAlignment(Pos.CENTER);
		playlistBox.setPrefWidth(sameWidth);
		
		HBox playerBox = new HBox();
		
		playButton = new Button();
		playButton.setId("play-button");
		playButton.getStyleClass().add("icon-button");
		playButton.getStyleClass().add("play-icon");
		
		skipButton = new Button ("");
		skipButton.setId("skip-button");
		skipButton.getStyleClass().add("icon-button");
		skipButton.getStyleClass().add("skip-icon");
		
		skipbackButton = new Button ("");
		skipbackButton.setId("skipback-button");
		skipbackButton.getStyleClass().add("icon-button");
		skipbackButton.getStyleClass().add("skipback-icon");
		
		playerBox.setPadding(sameInsets);
		playerBox.setSpacing(DISTANCE * 3);
		playerBox.getChildren().addAll(skipbackButton, playButton, skipButton);
		playerBox.setAlignment(Pos.CENTER);
		playerBox.setPrefWidth(sameWidth);
		
		HBox volumeBox = new HBox();
		Button volumeIcon = new Button("");
		volumeIcon.setId("volume");
		volumeIcon.getStyleClass().add("icon-button");
		volumeIcon.getStyleClass().add("volume-icon");
		
		volumeSlider = new Slider(0, 100, 50);
		volume = new Text("50");
		
		volumeBox.getChildren().addAll(volumeIcon, volumeSlider, volume);
		volumeBox.setPadding(sameInsets);
		volumeBox.setSpacing(DISTANCE);
		volumeBox.setPrefWidth(sameWidth);
		volumeBox.setAlignment(Pos.CENTER);
		
		bottomBox.getChildren().addAll(playlistBox, playerBox, volumeBox);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setSpacing(DISTANCE);
		bottomBox.setMinHeight(150);
	}
}
