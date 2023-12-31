package business;

import java.util.ArrayList;

/**Playlist Manager Klasse
 * Playlist kontrollieren
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
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
	
	public ArrayList <String> getTrackNames(){
		return playlist.getTrackNames();
	}
	
	public void setPlaylist(ArrayList<Track> newTracks) {
		playlist.setPlaylist(newTracks);
	}
}
