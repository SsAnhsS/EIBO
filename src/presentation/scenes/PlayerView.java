package presentation.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	public Button playButton;
	public Button skipButton;
	public Button skipbackButton;
	public Button shuffleButton;
	public Button repeatButton;
	
	public Slider musicProgress = new Slider(0, 100, 0);
	public Text time;
	public Slider volumeSlider = new Slider(0, 100, 60);
	public Text volume;
	
	public PlayerView() {
		//leftBox setup
		HBox leftBox = new HBox();
		setLeftBox(leftBox);
		this.setLeft(leftBox);
		
		//rightBox setup
		HBox rightBox = new HBox();
		setRightBox(rightBox);
		this.setRight(rightBox);
		
		//centerBox setup
		VBox centerBox = new VBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		//bottonBox setup
		VBox bottomBox = new VBox();
		setBottomBox(bottomBox);
		this.setBottom(bottomBox);
		
		BorderPane.setMargin(leftBox, new Insets(0,5,0,5));
		BorderPane.setMargin(rightBox, new Insets(0,5,0,5));
		BorderPane.setMargin(bottomBox, new Insets(5,0,5,0));
	}
	
	public void setLeftBox(HBox leftBox) {
		Button playlist = new Button ("Playlist");
		playlist.setId("playlist-button");
		
		leftBox.getChildren().addAll(playlist);
		leftBox.setAlignment(Pos.BASELINE_CENTER);
		leftBox.setPadding(sameInsets);
		leftBox.setStyle("-fx-background-color: white;");
	}
	
	public void setRightBox(HBox rightBox) {
		Button setting = new Button ("Setting");
		rightBox.getChildren().addAll(setting);
		rightBox.setAlignment(Pos.BASELINE_CENTER);
		rightBox.setPadding(sameInsets);
		rightBox.setStyle("-fx-background-color: white;");
		
	}
	
	public void setCenterBox(VBox centerBox) {
		//Cover Image setup
		Image image = new Image(getClass().getResourceAsStream("")); 
		//Path:/EIBO/src/data/images/img-test-1.jpg
		//Link Error make the Input Stream Null Error -> Need Fix
		ImageView imageView = new ImageView(image);
		ImageViewPane imagePane = new ImageViewPane(imageView);
		
		//nameBox setup
		VBox nameBox = new VBox();
		
		Label songName = new Label("Song Name");
		Label artistName = new Label("Artist Name");
		
		nameBox.getChildren().addAll(songName, artistName);
		
		nameBox.setPadding(sameInsets);
		nameBox.setAlignment(Pos.BASELINE_CENTER);
		nameBox.setSpacing(DISTANCE);
		
		centerBox.getChildren().addAll(imagePane, nameBox);
		centerBox.setAlignment(Pos.BASELINE_CENTER);
		centerBox.setPadding(sameInsets);
		centerBox.setStyle("-fx-background-color: white;");		
	}

	public void setBottomBox(VBox bottomBox) {
		
		//Music-Process-Bar setup
		HBox musicBar = new HBox();
		
		musicProgress.setPrefWidth(1000);
		time = new Text("0 / Total Time");
		
		musicBar.getChildren().addAll(musicProgress, time);
		musicBar.setAlignment(Pos.CENTER);
		musicBar.setSpacing(DISTANCE);
		
		//controlBox Setup
		HBox controlBox = new HBox();
		
		HBox volumeBox = new HBox();
		Label volumeLabel = new Label("Volume");
		volume = new Text("60");
		
		volumeBox.getChildren().addAll(volumeLabel, volumeSlider, volume);
		volumeBox.setPadding(sameInsets);
		volumeBox.setSpacing(DISTANCE);
		volumeBox.setPrefWidth(sameWidth);
		volumeBox.setAlignment(Pos.CENTER);
		volumeBox.setStyle("-fx-background-color: white;");
		
		HBox playerBox = new HBox();
		
		playButton = new Button("play");
		skipButton = new Button ("next");
		skipbackButton = new Button ("back");
		
		playerBox.setPadding(sameInsets);
		playerBox.setSpacing(DISTANCE);
		playerBox.getChildren().addAll(skipbackButton, playButton, skipButton);
		playerBox.setAlignment(Pos.CENTER);
		playerBox.setPrefWidth(sameWidth);
		playerBox.setStyle("-fx-background-color: white;");
		
		HBox shuffleBox = new HBox();
		
		shuffleButton = new Button ("shuffle"); 
		repeatButton = new Button("repeat");
		
		shuffleBox.setPadding(sameInsets);
		shuffleBox.setSpacing(DISTANCE);
		shuffleBox.getChildren().addAll(shuffleButton, repeatButton);
		shuffleBox.setAlignment(Pos.CENTER);
		shuffleBox.setPrefWidth(sameWidth);
		shuffleBox.setStyle("-fx-background-color: white;");
		
		controlBox.setPadding(sameInsets);
		controlBox.setSpacing(DISTANCE);
		controlBox.setAlignment(Pos.BOTTOM_CENTER);
		controlBox.getChildren().addAll(volumeBox, playerBox, shuffleBox);
		controlBox.setStyle("-fx-background-color: red;");
		
		bottomBox.getChildren().addAll(musicBar, controlBox);
		bottomBox.setPadding(sameInsets);
		bottomBox.setStyle("-fx-background-color: white;");		
	}
}
