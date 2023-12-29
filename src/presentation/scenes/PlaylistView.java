package presentation.scenes;

import java.util.List;

import business.Track;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PlaylistView extends TilePane{
	
	public final double DISTANCE = 10;
	
	public Label playlistName;
	
	public Button backButton;
	
	public ListView <Track> playlist;
	
	public PlaylistView() {
		VBox box = new VBox();
		
		TilePane playlistNameBox = new TilePane();
		playlistName = new Label("");
		backButton = new Button("");

		backButton.setId("back-button");
		backButton.getStyleClass().add("icon-button");
		backButton.getStyleClass().add("back-icon");
		
		playlistNameBox.setHgap(DISTANCE);
		playlistNameBox.setOrientation(Orientation.HORIZONTAL);
		playlistNameBox.getChildren().addAll(playlistName, backButton);
		
		playlist = new ListView<>();
		
		//playlist.getStyleClass().add("margin-5px");
		
		//leftBox.getStyleClass().add("margin-5px");
		box.getChildren().addAll(playlistNameBox, playlist);
		box.setMinWidth(1200);
		box.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(box);
	}
	
	
}
