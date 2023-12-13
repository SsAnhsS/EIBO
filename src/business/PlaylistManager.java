package business;

import java.util.ArrayList;

public class PlaylistManager {
	
	private Playlist playlist;
	private String playlistName;	
	private ArrayList<Track> tracks;
	
	
	public PlaylistManager() {
		playlistName = "test";
		playlist = new Playlist (playlistName);
		tracks = playlist.getTracks();
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
	
	public ArrayList<String> getTrackName(){
		return playlist.getTrackName();
	}
}
