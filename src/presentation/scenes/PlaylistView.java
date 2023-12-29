package presentation.scenes;

import business.Track;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**Playlist View Klasse
 * Playlist View einstellen
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class PlaylistView extends TilePane{
	
	public final double DISTANCE = 850;
			
	public Label playlistName;
	
	public Button backButton;
	
	public ListView <Track> playlist;
	
	public PlaylistView() {
		VBox box = new VBox();
		
		TilePane playlistNameBox = new TilePane();
		playlistName = new Label("");
		playlistName.setId("playlist-name");
		backButton = new Button("");

		backButton.setId("back-button");
		backButton.getStyleClass().add("icon-button");
		backButton.getStyleClass().add("back-icon");
		
		playlistNameBox.setHgap(DISTANCE);
		playlistNameBox.setOrientation(Orientation.HORIZONTAL);
		playlistNameBox.getChildren().addAll(playlistName, backButton);
		
		playlist = new ListView<>();
		playlist.setId("playlist-box");
		
		box.getChildren().addAll(playlistNameBox, playlist);
		box.setMinWidth(1200);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(20, 100, 20, 150));
		
		this.getChildren().addAll(box);
	}
	
	
}
