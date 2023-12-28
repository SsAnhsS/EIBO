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
import javafx.scene.text.Font;
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
		bottomBox.setId("bottom-box");
		
		
	}
	
	public void setLeftBox(HBox leftBox) {
		playlistButton = new Button ("");
		playlistButton.setId("playlist-button");
		playlistButton.getStyleClass().add("icon-button");
		playlistButton.getStyleClass().add("playlist-icon");
		
		leftBox.getChildren().addAll(playlistButton);
		leftBox.setAlignment(Pos.BASELINE_CENTER);
		leftBox.setPadding(sameInsets);
	}
	
	public void setRightBox(HBox rightBox) {
		settingButton = new Button ("");
		settingButton.setId("setting-button");
		settingButton.getStyleClass().add("icon-button");
		settingButton.getStyleClass().add("setting-icon");
		
		rightBox.getChildren().addAll(settingButton);
		rightBox.setAlignment(Pos.BASELINE_CENTER);
		rightBox.setPadding(sameInsets);
		
	}
	
	public void setCenterBox(VBox centerBox) {
		//Cover Image setup
		image = new Image(getClass().getResourceAsStream("")); 
		//file:///D:/GitHub/EIBO/src/data/images/img-test-1.jpg
		//..\\data\\images\\img-test-2.jpg
		//Path:/EIBO/src/data/images/img-test-1.jpg
		//C:\\Users\\Steph\\Documents\\GitHub\\EIBO\\src\\data\\images\\img-test-1.jpg
		//Link Error make the Input Stream Null Error -> Need Fix
		ImageView imageView = new ImageView(image);
		ImageViewPane imagePane = new ImageViewPane(imageView);
		this.setCenter(imagePane);
		
		//nameBox setup
		VBox nameBox = new VBox();
		
		songName = new Label("");
		songName.setId("song-name");
		
		artistName = new Label("");
		artistName.setId("artist-name");
		
		nameBox.getChildren().addAll(songName, artistName);
		
		nameBox.setPadding(sameInsets);
		nameBox.setAlignment(Pos.BASELINE_CENTER);
		nameBox.setSpacing(DISTANCE);
		
		centerBox.getChildren().addAll(imagePane, nameBox);
		centerBox.setAlignment(Pos.BASELINE_CENTER);
		centerBox.setPadding(sameInsets);		
	}

	public void setBottomBox(VBox bottomBox) {
		
		//Music-Process-Bar setup
		HBox musicBar = new HBox();
		
		musicProgress.setPrefWidth(1000);
		time = new Text("");
		
		musicBar.getChildren().addAll(musicProgress, time);
		musicBar.setAlignment(Pos.CENTER);
		musicBar.setSpacing(DISTANCE);
		
		//controlBox Setup
		HBox controlBox = new HBox();
		
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
		shuffleBox.setAlignment(Pos.CENTER);
		shuffleBox.setPrefWidth(sameWidth);
		
		controlBox.setPadding(sameInsets);
		controlBox.setSpacing(DISTANCE);
		controlBox.setAlignment(Pos.BOTTOM_CENTER);
		controlBox.getChildren().addAll(volumeBox, playerBox, shuffleBox);
		
		bottomBox.getChildren().addAll(musicBar, controlBox);
		bottomBox.setPadding(sameInsets);	
	}
}
