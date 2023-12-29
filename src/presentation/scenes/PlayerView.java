package presentation.scenes;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mp3player.scene.layout.ImageViewPane;

public class PlayerView extends BorderPane{
	
	public final double DISTANCE = 10;
	
	public Button playlistButton;
	public Button settingButton;
	
	public Insets sameInsets = new Insets(10);
	public double sameWidth = 400;
	
	public Label songName;
	public Label artistName;
	
	public Image image;
	
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
	
	public void setTopBox(HBox topBox) {
		VBox leftBox = new VBox();
		
		VBox nameBox = new VBox();
		
		songName = new Label("");
		songName.setId("song-name");
		
		artistName = new Label("");
		artistName.setId("artist-name");
		
		nameBox.getChildren().addAll(songName, artistName);
		
		nameBox.setPadding(sameInsets);
		nameBox.setAlignment(Pos.CENTER_RIGHT);
		nameBox.setSpacing(DISTANCE);
		nameBox.setMinHeight(500);
		
		HBox shuffleBox = new HBox();
		
		shuffleButton = new Button (""); 
		shuffleButton.setId("shuffle-button");
		shuffleButton.getStyleClass().add("shuffle-icon");
		
		repeatButton = new Button("");
		repeatButton.setId("repeat-button");
		repeatButton.getStyleClass().add("repeat-icon");
		
		shuffleBox.setPadding(sameInsets);
		shuffleBox.setSpacing(DISTANCE);
		shuffleBox.getChildren().addAll(shuffleButton, repeatButton);
		shuffleBox.setAlignment(Pos.CENTER_LEFT);
		
		leftBox.getChildren().addAll(nameBox, shuffleBox);
		leftBox.setMinWidth(700);
		leftBox.setPadding(sameInsets);
		
		//Cover Image setup
		VBox rightBox = new VBox();
		image = new Image(getClass().getResourceAsStream(""));
		//new FileInputStream("src\\data\\images\\img-test-1.jpg")
		//file:///D:/GitHub/EIBO/src/data/images/img-test-1.jpg
		//..\\data\\images\\img-test-2.jpg
		//Path:/EIBO/src/data/images/img-test-1.jpg
		//C:\\Users\\Steph\\Documents\\GitHub\\EIBO\\src\\data\\images\\img-test-1.jpg		
		//Link Error make the Input Stream Null Error -> Need Fix		
		ImageView imageView = new ImageView(image);
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMinSize(400, 400);
		imagePane.setStyle("-fx-background-color: #ffffff");
		
		rightBox.getChildren().add(imagePane);
		
		rightBox.setAlignment(Pos.CENTER);
		
		topBox.getChildren().addAll(leftBox, rightBox);
		topBox.setAlignment(Pos.CENTER);
		topBox.setSpacing(DISTANCE);
		topBox.setMinHeight(600);
	}
	
	public void setCenterBox(HBox centerBox) {
		musicProgress = new ProgressBar(0);
		musicProgress.setPrefWidth(1100);
		musicProgress.setPrefHeight(12);
		time = new Text("");
		
		centerBox.getChildren().addAll(musicProgress, time);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(DISTANCE);
	}
	
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
		skipButton.getStyleClass().add("skip-icon");
		
		skipbackButton = new Button ("");
		skipbackButton.setId("skipback-button");
		skipbackButton.getStyleClass().add("skipback-icon");
		
		playerBox.setPadding(sameInsets);
		playerBox.setSpacing(DISTANCE);
		playerBox.getChildren().addAll(skipbackButton, playButton, skipButton);
		playerBox.setAlignment(Pos.CENTER);
		playerBox.setPrefWidth(sameWidth);
		
		HBox volumeBox = new HBox();
		Button volumeIcon = new Button("");
		volumeIcon.setId("volume");
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
