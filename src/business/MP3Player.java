package business;

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
	
	public void select(Track track) {
		this.track = track;
		audioPlayer = minim.loadMP3File(track.getSoundFile());
		play();
	}
	
	public void play(String fileName) {
		audioPlayer = minim.loadMP3File(fileName);
		play();
	}
	
	public class PlayThread extends Thread{
		public void run() {
			playAudio();
		}
	}
	
	private void playAudio() {
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("playing " + i);
		}
	}
	
	public void play() {
		audioPlayer.play();
		PlayThread playThread = new PlayThread();
		
		playThread.setDaemon(true);
		playThread.start();
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
