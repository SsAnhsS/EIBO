package presentation.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import business.Track;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mp3player.scene.layout.ImageViewPane;

public class TrackCell extends ListCell<Track>{
	
	public final double DISTANCE = 10;
	public Insets sameInsets = new Insets(5);
	
	HBox trackBox;
	
	VBox infoPane;
	Label title;
	Label artist;
	ImageView imageView;
	
	public TrackCell() {
		infoPane = new VBox();
		title = new Label();
		artist = new Label();
		infoPane.getChildren().addAll(title, artist);
		
		trackBox = new HBox();
		
		imageView = new ImageView();
		
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMaxSize(100, 100);
		
		trackBox.getChildren().addAll(imagePane, infoPane);
		
		trackBox.setSpacing(DISTANCE);
		trackBox.setPadding(sameInsets);
	}
	
	protected void updateItem(Track item, boolean empty) {
		super.updateItem(item, empty);
		
		if (!empty) {
			title.setText(item.getTitle());
			artist.setText(item.getArtist());
			
			try {
				imageView.setImage(new Image(new FileInputStream(item.getPhotoCover())));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.setGraphic(trackBox);
		} else {
			this.setGraphic(null);
		}
		
	}
}
