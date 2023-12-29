package business;

import java.util.ArrayList;

import de.hsrm.mi.prog.util.StaticScanner;

/**KeyboardController Klasse, um Mp3 Player in der Console zu kontrollieren
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class KeyboardController {
	
	private MP3Player mp3Player;
	private PlaylistManager playlistManager;
	
	public void start() {
		mp3Player = new MP3Player();
		playlistManager = new PlaylistManager();
		
		ArrayList <String> tracksName = playlistManager.getTrackNames();
		
		String playingFile = "01 Bring Mich Nach Hause.mp3";
		
		String line;
		do {
			for(String s : tracksName) {
				System.out.println((tracksName.indexOf(s) + 1) + " " + s);
			}
			System.out.print("Kommand: ");
			line = StaticScanner.nextString();
			
			if (line.contentEquals("quit")) {
				System.out.println("End!");
				break;
			}
			
			String [] commands = line.split(" ");
			
			switch (commands[0]) {
			case "play":
				if (commands.length > 1) {
					playingFile = mergeName(commands) + ".mp3";
				}
				mp3Player.play("src//data//mp3-songs//" + playingFile);
				break;
			case "pause":
				mp3Player.pause();
				break;
			case "volume":
				mp3Player.volume(Float.valueOf(commands[1]));
				break;
			}
		} while(true);
		
	}
	
	/**
	 * Song-Name mergen aus String Array von Kommand
	 * @param String Array
	 * @return Name
	 */
	public String mergeName (String [] commands) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < commands.length; i++) {
			sb.append(commands[i]);
			if (i!= commands.length - 1)
				sb.append(" ");
		}
		
		return sb.toString();
	}
}
