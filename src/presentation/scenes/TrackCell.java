package presentation.scenes;

import business.Track;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TrackCell extends ListCell<Track>{
	
	public final double DISTANCE = 10;
	public Insets sameInsets = new Insets(5);
	
	HBox trackBox;
	
	VBox infoPane;
	Label title;
	Label artist;
	CheckBox check;
	
	public TrackCell() {
		infoPane = new VBox();
		title = new Label();
		artist = new Label();
		infoPane.getChildren().addAll(title, artist);
		
		trackBox = new HBox();
		check = new CheckBox();
		
		trackBox.getChildren().addAll(check, infoPane);
		
		trackBox.setSpacing(DISTANCE);
		trackBox.setPadding(sameInsets);
	}
	
	protected void updateItem(Track item, boolean empty) {
		super.updateItem(item, empty);
		
		if (!empty) {
			title.setText(item.getTitle());
			artist.setText(item.getArtist());
			check.setSelected(false);
			
			this.setGraphic(trackBox);
		} else {
			this.setGraphic(null);
		}
		
	}
}
