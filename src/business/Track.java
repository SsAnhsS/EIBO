package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


public class Track {
	
	private Mp3File mp3File;
	private String title;
	private int length;
	private String artist;
	private String albumTitle;
	private String photoCover; //cover image link
	private String soundFile;
	
	
	public Track(int id, String title, int length, String artist, String albumTitle, String soundFile) {
		this.title = title;
		this.length = length;
		this.artist = artist;
		this.albumTitle = albumTitle;
		this.soundFile = soundFile;
	}
	
	public Track(String soundFile) {
		
		try {
			mp3File = new Mp3File(soundFile);
			length = (int) mp3File.getLengthInSeconds();
			if(mp3File.hasId3v1Tag()) {
				ID3v1 id3v1Tag = mp3File.getId3v1Tag();
				title = id3v1Tag.getTitle();
				artist = id3v1Tag.getArtist();
				albumTitle = id3v1Tag.getAlbum();
			}
			
			//byte[] albumImageDate = idv2Tag.getAlbumImage();
			
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void setSoundFile(String file) {
		soundFile = file;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String albumTitle() {
		return albumTitle;
	}
	
	public String soundFile() {
		return soundFile;
	}
}
