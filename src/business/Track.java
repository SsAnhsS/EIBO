package business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


public class Track {
	
	private Mp3File mp3File;
	private String title;
	private int length;
	private String artist;
	private String albumTitle;
	private String photoCover;
	private String soundFile;
	
	private File outputFile;
	
	public Track(String soundFile) {
		this.soundFile = soundFile;
		
		try {
			mp3File = new Mp3File(soundFile);
			length = (int) mp3File.getLengthInSeconds();
			if(mp3File.hasId3v2Tag()) {
				ID3v2 id3v2Tag = mp3File.getId3v2Tag();
				title = id3v2Tag.getTitle();
				artist = id3v2Tag.getArtist();
				albumTitle = id3v2Tag.getAlbum();
				byte[] imageData = id3v2Tag.getAlbumImage();
				saveImage(byteArrayToImage(imageData));
				//save in folder in .jpg form and get that by link
			}
			
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
	
	public String getSoundFile() {
		return soundFile;
	}
	
	public void setSoundFile(String file) {
		soundFile = file;
	}

	public BufferedImage byteArrayToImage(byte[] bytes){  
        BufferedImage bufferedImage = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
			bufferedImage = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return bufferedImage;
	}
	
	public void saveImage(BufferedImage bufferedImage) {
		outputFile = new File("src/data/images/" + getTitle() + ".jpg");
		try {
			ImageIO.write(bufferedImage, "jpg", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPhotoCover() {
		return "src/data/images/" + outputFile.getName();
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
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	
}
