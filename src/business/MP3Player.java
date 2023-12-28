package business;

import java.util.ArrayList;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class MP3Player {
	
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	
	private PlaylistManager playlistManager;
	public Playlist playlist;
	public Track track;
	
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
	
	public void play(String fileName) {
		audioPlayer = minim.loadMP3File(fileName);
		play();
	}
	
	public void play() {
		audioPlayer.play();
	}
	
	public void pause() {
		audioPlayer.pause();
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
	
	public void volume(float value) {
		//audioPlayer.setVolume(value); //minim error, Volume is not supported
		audioPlayer.setGain(value); //not change the value
	}
}
