package presentation.scenes;

import java.util.ArrayList;

import business.MP3Player;
import business.Playlist;
import business.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PlaylistViewController {

	PlaylistView playlistView;
	
	Label playlistName;
	Button playerButton;
	ListView <Track> playlist;
	
	private MP3Player player;
	
	ArrayList <Track> tracks;
	
	public PlaylistViewController(MP3Player player) {
		playlistView = new PlaylistView();
		
		playlistName = playlistView.playlistName;
		playerButton = playlistView.playerButton;
		playlist = playlistView.playlist;
		
		playlistName.setText(player.playlist.getPlaylistName());
		
		this.player = player;
		
		initialize();
	}
	
	public void initialize() {
		tracks = player.playlist.getTracks();
		
		playlist.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
				return new TrackCell();
			}
		});
		
		playlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldTrack, Track newTrack) {
				player.play(newTrack.getSoundFile());
				
			}
			
		});
		
		ObservableList <Track> playlistModel = playlist.getItems();
		playlistModel.addAll(tracks);
		
	}
	
	public Pane getRoot() {
		return playlistView;
	}
}
