package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Playlist {
	private String playlistName;
	private ArrayList<Track> tracks;
	private Date creationDate; //LocalDate date = LocalDate.now();
	private String file;
	
	
	public Playlist(String playlistName, String file) {
		this.playlistName = playlistName;
		this.file = file;
		tracks = new ArrayList<Track>();
		savePlaylist();
	}
	
	public void savePlaylist() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while(line != null) {
				String trackFile = line;
				Track newTrack = new Track(trackFile);
				tracks.add(newTrack);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Track getTrack(int number) {
		for(Track aktTrack : tracks) {
			if(number == tracks.indexOf(aktTrack) + 1)
				return aktTrack;
		}
		return null;
	}
	
	public int numberOfTracks() {
		return tracks.size();
	}
	
	public ArrayList<Track> getTracks(){
		return (ArrayList<Track>) tracks;
	}
	
	public ArrayList<String> getTrackName(){
		ArrayList<String> names = new ArrayList<>();
		for(Track aktTrack : tracks) {
			names.add(aktTrack.getTitle());
		}
		return names;
	}
	
	public void setPlaylistName(String name) {
		playlistName = name;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
}
