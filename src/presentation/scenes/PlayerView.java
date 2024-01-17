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
	
	public Button shuffleButton;
	public Button repeatButton;
	

	public Button playButton;
	public Button skipButton;
	public Button skipbackButton;
	
	public Slider musicProgress;
	public Text time;
	public Slider volumeSlider;
	public Text volume;
	public int defaultVolume = 50;
	
	ControlView controlView;
	
	public PlayerView() {
		
		HBox topBox = new HBox();
		setTopBox(topBox);
		this.setTop(topBox);
		
		controlView = new ControlView();
		playlistButton = controlView.playlistButton;
		
		playButton = controlView.playButton;
		skipButton = controlView.skipButton;
		skipbackButton = controlView.skipbackButton;
		
		musicProgress = controlView.musicProgress;
		time = controlView.time;
		volumeSlider = controlView.volumeSlider;
		volume = controlView.volume;
		
		this.setBottom(controlView);
		this.setPadding(new Insets(0, 0, 0, 50));
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
		repeatButton.getStyleClass().add("repeat-one-icon");
		repeatButton.setOpacity(0.5);
		
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
	
	
}
