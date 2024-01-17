package business;

import java.util.ArrayList;
import java.util.Collections;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**Mp3-Player Klasse
 * Funktionen von Mp3-Player einstellen
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class MP3Player {
	
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	
	private PlaylistManager playlistManager;
	
	public int defaultVolume = 50;
	
	private int indexTrack = 0;
	
	private Thread playingSong;
	private Thread playingTime;
	private long position = 0;
	
	private boolean isRepeating = false;
	
	private SimpleIntegerProperty time;
	private SimpleObjectProperty <Playlist> currentPlaylist;
	private SimpleObjectProperty <Track> currentTrack;
	private SimpleBooleanProperty isPlaying;
	
	/**
	 * Erstellen einen neuen MP3Player
	 */
	public MP3Player() {
		minim = new SimpleMinim(true);
		
		time = new SimpleIntegerProperty();
		currentTrack = new SimpleObjectProperty<Track>();
		currentPlaylist = new SimpleObjectProperty<Playlist>();
		isPlaying = new SimpleBooleanProperty();
		isPlaying.set(false);
		
		playlistManager = new PlaylistManager();
		
		currentPlaylist.set(playlistManager.getPlaylist());
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
		
		audioPlayer = minim.loadMP3File(currentTrack.getValue().getSoundFile());
		volume(defaultVolume);
	}
	
	
	public void play(String fileName) {
		if(isPlaying.getValue()) {
			pause();
		}
		
		indexTrack = currentPlaylist.getValue().getIndex(fileName);
		audioPlayer = minim.loadMP3File(fileName);
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
		this.position = 0;
		play();
	}
	
	public class PlayThread extends Thread{
		public void run() {
			audioPlayer.play((int) position);
			
//			skip();
			
		}
	}
	
	public void play() {
		//audioPlayer.play();
		
		if(currentPlaylist.getValue() != null) {
			isPlaying.set(true);
			
			//startet neuen PlayThread
			playingSong = new PlayThread();
			playingSong.start();
			currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
			
			//Thread, um playing time zu zaehlen
			playingTime = new Thread() {
				public void run() {
					//solange der Thread nicht interrupted, playing time ist zaehlt
					while(!isInterrupted()) {
						
						//rechnet aktuelle playing time in Sekunden um
						int pos = (int) (position/1000);
						//zaehlt playing time dann pro Sekunden und updated damit die TimeProperty
						for(int i = pos; i <= currentTrack.getValue().getLength()
								&& audioPlayer.isPlaying(); i++) {
							time.set(i);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								interrupt();
							}
						}
					}
				}
			};
			playingTime.start();
		}
		
	}
	
	/**
	 * Pausieren des aktuellen Tracks
	 */
	public void pause() {
		playingTime.interrupt();
		playingSong.interrupt();
		audioPlayer.pause();
		isPlaying.set(false);
		this.position = audioPlayer.position();
	}
	
	public void skip() {
		
		if(isRepeating) {
			if(isPlaying.getValue()) {
				pause();
			}
			this.position = 0;
			play();
			return;
		}
		else {
			if(isPlaying.getValue()) {
				pause();
			}
			if(indexTrack + 1 < currentPlaylist.getValue().totalTracks()) {
				indexTrack ++;
			}
			else {
				indexTrack = 0;
			}
			this.position = 0;
			play(currentPlaylist.getValue().getTrack(indexTrack).getSoundFile());

		}
		
		
	}
	
	public void skipback() {
		
		if(isRepeating) {
			if(isPlaying.getValue()) {
				pause();
			}
			this.position = 0;
			play();
			return;
		}
		else {
			if(isPlaying.getValue()) {
				pause();
			}
			if(indexTrack > 0) {
				indexTrack --;
			}
			else {
				indexTrack = currentPlaylist.getValue().totalTracks() - 1;
			}
			this.position = 0;
			play(currentPlaylist.getValue().getTrack(indexTrack).getSoundFile());
		}
	}
	
	public void shuffle() {
		Playlist newPlaylist = new Playlist("Test01");
		Playlist playlist = currentPlaylist.getValue();
		ArrayList <Track> tracks = playlist.getTracks();
		Collections.shuffle(tracks);
		playlist.setPlaylist(tracks);
		newPlaylist.setPlaylist(tracks);
		currentPlaylist.set(newPlaylist);
		playlistManager.setPlaylist(tracks);
		
		System.out.println("Shuffle done!");
		for(Track aktTrack: currentPlaylist.getValue().getTracks()) {
			System.out.println(aktTrack.getTitle());
		}
		
	}
	
	public void volume(int value) {
		//Weil Gain Value hat Range [-50;0]
		float gainValue = (float) (value-100)/2;
		if(gainValue == -50) {
			audioPlayer.mute();
		} else {
			if(audioPlayer.isMuted()) {
				audioPlayer.unmute();
			}
			audioPlayer.setGain(gainValue);
		}
	}
	
	public void select(Track track) {
		if(isPlaying.getValue()) {
			pause();
		}
		currentTrack.set(track);
		this.position = 0;
		audioPlayer = minim.loadMP3File(track.getSoundFile());
		play();
	}
	
	public boolean getRepeat() {
		return isRepeating;
	}
	
	public boolean setRepeat(Boolean value) {
		this.isRepeating = value;
		return value;
	}
	
	/**
	 * timeProperty()
	 * @return
	 */
	public SimpleIntegerProperty getTimeProperty() {
		return time;
	}
	
	public SimpleObjectProperty <Track> getTrackProperty(){
		return currentTrack;
	}
	
	public SimpleObjectProperty <Playlist> getPlaylistProperty(){
		return currentPlaylist;
	}
	
	public SimpleBooleanProperty getPlayingProperty() {
		return isPlaying;
	}
	
	public void setCurrentTrack(Track track) {
		currentTrack.set(track);
	}
	
	public void setCurrentPlaylist(Playlist playlist) {
		currentPlaylist.set(playlist);
	}
	
	public void setPlayingProperty(Boolean value) {
		isPlaying.set(value);
	}
	
}
