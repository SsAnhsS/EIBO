package business;

import java.util.ArrayList;
import java.util.Collections;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

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
	public Playlist playlist;
	public Track track;
	
	public boolean isPlaying = false;
	
	public MP3Player() {
		playlistManager = new PlaylistManager();
		playlist = playlistManager.getPlaylist();
		track = playlist.getTrack(0);
		minim = new SimpleMinim(true);
		audioPlayer = new SimpleAudioPlayer(null, false);
		audioPlayer = minim.loadMP3File(track.getSoundFile());
	}
	
	public void setPlaylist(Playlist list) {
		playlist = list;
	}
	
	public void select(Track track) {
		this.track = track;
		audioPlayer = minim.loadMP3File(track.getSoundFile());
		play();
	}
	
	public void play(String fileName) {
		audioPlayer = minim.loadMP3File(fileName);
		play();
	}
	
//	public class PlayThread extends Thread{
//		public void run() {
//			playAudio();
//		}
//	}
//	
//	private void playAudio() {
//		for(int i = 0; i < 10; i++) {
//			try {
//				Thread.sleep(1000);
//			}
//			catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("playing " + i);
//		}
//	}
	
	public void play() {
		audioPlayer.play();
		isPlaying = true;
//		PlayThread playThread = new PlayThread();
//		
//		playThread.setDaemon(true);
//		playThread.start();
	}
	
	public void pause() {
		audioPlayer.pause();
		isPlaying = false;
	}
	
	public void skip() {
		pause();
		int index = playlist.getIndex(track);
		
		if(index < playlist.totalTracks() - 1) {
			track = playlist.getTrack(index + 1);
		} else track = playlist.getTrack(0);
		
		play(track.getSoundFile());
	}
	
	public void skipback() {
		pause();
		int index = playlist.getIndex(track);
		
		if(index > 0) {
			track = playlist.getTrack(index - 1);
		} else track = playlist.getTrack(playlist.totalTracks() - 1);
		
		play(track.getSoundFile());
	}
	
	public void shuffle() {
		ArrayList <Track> tracks = playlist.getTracks();
		Collections.shuffle(tracks);
		playlist.setPlaylist(tracks);
		playlistManager.setPlaylist(tracks);
		System.out.println("Shuffle done!");
		for(Track aktTrack: playlist.getTracks()) {
			System.out.println(aktTrack.getTitle());
		}
		
	}
	
	public void repeat() {
		
	}
	
	public void volume(float value) {
		//audioPlayer.setVolume(value); //minim error, Volume is not supported
		audioPlayer.setGain(value);
	}
	
}
