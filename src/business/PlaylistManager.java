package business;

import java.util.ArrayList;

public class PlaylistManager {
	
	private Playlist playlist;
	private String playlistName;
	
	
	public PlaylistManager() {
		playlistName = "Test";
		playlist = new Playlist (playlistName);
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
}
