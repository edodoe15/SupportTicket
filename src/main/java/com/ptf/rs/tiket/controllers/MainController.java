package com.ptf.rs.tiket.controllers;

import java.io.IOException;

import com.ptf.rs.tiket.TiketFX;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	@FXML
	private BorderPane mainBorder;
	
	@SuppressWarnings("exports")
	public BorderPane getMainBorder() {
		return mainBorder;
	}

	public void pregledajTikete() throws IOException {
		TiketFX.setContent("Main");
	}
	
	public void dodajTiket() throws IOException {
		TiketFX.setContent("Tiket");
	}
	
	public void showAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Aplikacija tiket verzija 1.0.0");
		alert.setTitle("Support ticket");
		alert.setHeaderText(":)");
		
		alert.show();
	}
}
