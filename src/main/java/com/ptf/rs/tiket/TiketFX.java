package com.ptf.rs.tiket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import com.ptf.rs.tiket.controllers.MainController;

public class TiketFX extends Application {

	    private static Scene scene;
	    private static FXMLLoader loader;

	    @Override
	    public void start(Stage stage) throws IOException {
	        scene = new Scene(loadFXML("Main"));
	        
	        stage.setScene(scene);
	        stage.setMaximized(true);
	        stage.show();

	    }

	    public static void setRoot(String fxml) throws IOException {
	        scene.setRoot(loadFXML(fxml));
	    }
	    
	    public static void setContent(String xml) throws IOException {
	    	Parent pane = loadFXML(xml);
	    }
	    
	    @SuppressWarnings("exports")
		public static FXMLLoader getLoader() {
	    	return loader;
	    }

	    private static Parent loadFXML(String fxml) throws IOException {
	        loader = new FXMLLoader(TiketFX.class.getResource("views/" + fxml + ".fxml"));
	        return loader.load();
	    }

	    public static void main(String[] args) {
	        launch();
	    }

}
