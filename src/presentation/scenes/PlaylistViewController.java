package presentation.scenes;

import java.util.ArrayList;

import application.MP3_App;
import application.ViewName;
import business.MP3Player;
import business.Playlist;
import business.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**Playlist View Controller Klasse
 * Playlist View kontrollieren
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class PlaylistViewController {

	PlaylistView playlistView;
	
	Label playlistName;
	Button backButton;
	
	ListView <Track> playlist;
	
	private MP3_App app;
	private MP3Player player;
	private Thread updatePlaylist;
	private ArrayList <Track> tracks;
	
	public PlaylistViewController(MP3_App app, MP3Player player) {
		this.player = player;
		this.app = app;
		
		playlistView = new PlaylistView();
		
		playlistName = playlistView.playlistName;
		backButton = playlistView.backButton;
		playlist = playlistView.playlist;
		
		playlistName.setText(player.getPlaylistProperty().getValue().getPlaylistName());
		
		updatePlaylist(player.getPlaylistProperty().getValue());
		initialize();
	}
	
	public void initialize() {
		
		playlist.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
				return new TrackCell();
			}
		});
		
		playlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		playlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldTrack, Track newTrack) {
				
		        player.select(newTrack);
			}
			
		});
		
		//Listener für TrackProperty
		player.getTrackProperty().addListener(new ChangeListener<Track>() {
			//update Titel, Image Cover und Music Progress
			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldValue, Track newValue) {
				Platform.runLater(() -> player.setCurrentTrack(newValue));
			}
			
		});
		
		//Listener für PlaylistProperty
		player.getPlaylistProperty().addListener(new ChangeListener<Playlist>() {

			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				//Platform.runLater(() -> player.setCurrentPlaylist(newValue));
				
				Platform.runLater(new Runnable() {
					public void run() {
						//player.setCurrentTrack(newValue);
//						tracks = player.getPlaylistProperty().getValue().getTracks();
//						
//						for(Track aktTrack: player.getPlaylistProperty().getValue().getTracks()) {
//							System.out.println(aktTrack.getTitle());
//						}
//						
//						ObservableList <Track> playlistModel = playlist.getItems();
//						playlistModel.addAll(tracks);
						
						updatePlaylist(newValue);
					}
				});
			}
			
		});
		
		backButton.setOnAction(event -> {
			app.switchView(ViewName.PlayerView);
		});
	}
	
	public void updatePlaylist(Playlist list) {
		//player.getPlaylistProperty().setValue(list);
		tracks =list.getTracks();
		ObservableList <Track> playlistModel = playlist.getItems();
		playlistModel.clear();
		playlistModel.setAll(tracks);
		playlist.setItems(playlistModel);
	}
	
	public Pane getRoot() {
		return playlistView;
	}
}
