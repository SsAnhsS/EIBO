package presentation.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mp3player.scene.layout.ImageViewPane;

/**Player View Klasse
 * Player View einstellen
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class PlayerView extends BorderPane{
	
	public final double DISTANCE = 10;
	
	public Button playlistButton;
	
	public Insets sameInsets = new Insets(10);
	public double sameWidth = 400;
	
	public Label songName;
	public Label artistName;
	public Label albumName;
	
	public ImageView imageView;
	
	public Button playButton;
	public Button skipButton;
	public Button skipbackButton;
	public Button shuffleButton;
	public Button repeatButton;
	
	public ProgressBar musicProgress;
	public Text time;
	public Slider volumeSlider = new Slider(0, 100, 60);
	public Text volume;
	
	public PlayerView() {
		
		HBox topBox = new HBox();
		setTopBox(topBox);
		this.setTop(topBox);
		
		HBox centerBox = new HBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		HBox bottomBox = new HBox();
		setBottomBox(bottomBox);
		this.setBottom(bottomBox);
	}
	
	/**
	 * Top-Box von Player View einstellen
	 * Top-Box:
	 * 		- left-box
	 * 			+ name-box
	 * 			+ shuffle-box
	 * 		- right-box
	 * 			+ image-view-pane
	 * @param topBox
	 */
	public void setTopBox(HBox topBox) {
		//Left-Box einstellen
		VBox leftBox = new VBox();
		
		VBox nameBox = new VBox();
		
		songName = new Label("");
		songName.setId("song-name");
		
		artistName = new Label("");
		
		albumName = new Label("");
		
		nameBox.getChildren().addAll(songName, artistName, albumName);
		
		nameBox.setPadding(sameInsets);
		nameBox.setAlignment(Pos.CENTER_RIGHT);
		nameBox.setSpacing(DISTANCE);
		nameBox.setMinHeight(500);
		
		HBox shuffleBox = new HBox();
		
		shuffleButton = new Button (""); 
		shuffleButton.setId("shuffle-button");
		shuffleButton.getStyleClass().add("icon-button");
		shuffleButton.getStyleClass().add("shuffle-icon");
		
		repeatButton = new Button("");
		repeatButton.setId("repeat-button");
		repeatButton.getStyleClass().add("icon-button");
		repeatButton.getStyleClass().add("repeat-icon");
		
		shuffleBox.setSpacing(DISTANCE);
		shuffleBox.getChildren().addAll(shuffleButton, repeatButton);
		shuffleBox.setAlignment(Pos.CENTER_LEFT);
		
		leftBox.getChildren().addAll(nameBox, shuffleBox);
		leftBox.setMinWidth(700);
		leftBox.setPadding(sameInsets);
		
		//Right-Box einstellen
		VBox rightBox = new VBox();
		
		imageView = new ImageView();
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMaxSize(400, 400);
		
		rightBox.getChildren().add(imagePane);
		
		rightBox.setAlignment(Pos.CENTER);
		
		topBox.getChildren().addAll(leftBox, rightBox);
		topBox.setAlignment(Pos.CENTER);
		topBox.setSpacing(DISTANCE);
		topBox.setMinHeight(600);
	}
	
	/**
	 * Center-Box von Player View einstlellen
	 * 
	 * @param centerBox
	 */
	public void setCenterBox(HBox centerBox) {
		musicProgress = new ProgressBar(0);
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
		
		volume = new Text("60");
		
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
