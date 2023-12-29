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
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PlaylistViewController {

	PlaylistView playlistView;
	
	Label playlistName;
	Button backButton;
	ListView <Track> playlist;
	
	private MP3_App app;
	private MP3Player player;
	
	ArrayList <Track> tracks;
	
	public PlaylistViewController(MP3_App app, MP3Player player) {
		this.player = player;
		this.app = app;
		
		playlistView = new PlaylistView();
		
		playlistName = playlistView.playlistName;
		backButton = playlistView.backButton;
		playlist = playlistView.playlist;
		
		playlistName.setText(player.playlist.getPlaylistName());
		
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
				player.select(newTrack);
				
			}
			
		});
		
		ObservableList <Track> playlistModel = playlist.getItems();
		playlistModel.addAll(tracks);
		
		Thread deleteThread = new Thread(() -> {
			  while(playlistModel.size() > 5) {
				  try {
					Platform.runLater(() -> playlistModel.remove(0));
					Thread.sleep(500);
				  } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			  }
		});
			
		//deleteThread.start();
		
		backButton.setOnAction(event -> {
			app.switchView(ViewName.PlayerView);
		});
	}
	
	public Pane getRoot() {
		return playlistView;
	}
}
