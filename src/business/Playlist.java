package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Playlist {
	private String playlistName;
	private ArrayList<Track> tracks;
	private Date creationDate; //LocalDate date = LocalDate.now();
	private String file = "src\\data\\playlist\\test.m3u";
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
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
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + file);
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Track getTrack(int n) {
		for(Track aktTrack : tracks) {
			if(n == tracks.indexOf(aktTrack)) {
				return aktTrack;
			}
		}
		return null;
	}
	
	public int getIndex(Track track) {
		int index = 0;
		for(Track aktTrack : tracks) {
			if(track.equals(aktTrack)) {
				index = tracks.indexOf(aktTrack);
			}
		}
		return index;
	}
	
	public int totalTracks() {
		return tracks.size();
	}
	
	public ArrayList<Track> getTracks(){
		return (ArrayList<Track>) tracks;
	}
	
	public ArrayList<String> getTrackNames(){
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
