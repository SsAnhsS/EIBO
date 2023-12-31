package application;

import java.util.HashMap;

import business.MP3Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.PlayerViewController;
import presentation.scenes.PlaylistViewController;

/**Mp3 Application
 * 
 * @author Thi Hai Anh, Luong _ 1176913
 * @author Khanh Linh, Truong _ 1257179
 */
public class MP3_App extends Application{
	
	private Stage primaryStage;
	private HashMap<ViewName, Pane> views;
	
	private Pane playerView;
	private Pane playlistView;
	
	PlayerViewController playerViewController;
	PlaylistViewController playlistViewController;
	
	MP3Player player;
	
	public void init() {
		player = new MP3Player();
		views = new HashMap<>();
		
		playerViewController = new PlayerViewController(this, player);
		playerView = playerViewController.getRoot();
		views.put(ViewName.PlayerView, playerView);
		
		playlistViewController = new PlaylistViewController(this, player);
		playlistView = playlistViewController.getRoot();
		views.put(ViewName.PlaylistView, playlistView);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Pane root = new Pane();
			
			Scene scene = new Scene(root, 1280, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			switchView(ViewName.PlayerView);
			
			primaryStage.setTitle("MP3 Application");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Scene auswählen
	 * @param ViewName
	 */
	public void switchView(ViewName name) {
		Scene currentScene = primaryStage.getScene();
		
		Pane nextView = views.get(name);
		
		if(nextView != null) {
			currentScene.setRoot(nextView);
		}
	}
	
	public void stop() {
		System.exit(0);
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
