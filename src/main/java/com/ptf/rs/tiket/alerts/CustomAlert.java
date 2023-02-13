package com.ptf.rs.tiket.alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class CustomAlert {
	
	public static Optional<ButtonType> showConfirmation(String headerText, String title, String contentText,
			String okText, String cancelText) {

		if (headerText.isEmpty()) {
			headerText = "Potvrda";
		}
		if (title.isEmpty()) {
			title = "Potvrda";
		}
		if (contentText.isEmpty()) {
			contentText = "Da li ste sigurni?";
		}
		if (okText.isEmpty()) {
			okText = "Uredu";
		}
		if (cancelText.isEmpty()) {
			cancelText = "Odustani";
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		okButton.setText(okText);
		Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
		cancelButton.setText(cancelText);

		return alert.showAndWait();
	}

	public static void show(String headerText, String title, String contentText, Alert.AlertType type) {

		if (headerText.isEmpty()) {
			headerText = "Informacija";
		}
		if (title.isEmpty()) {
			title = "Informacija";
		}
		if (contentText.isEmpty()) {
			contentText = "Informacija";
		}

		Alert alert = new Alert(type);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText(headerText);

		alert.show();
	}

	public static String showPrompt(String headerText, String title, String contentText, String okText,
			String cancelText, String currentValue) {
		TextInputDialog dialog = new TextInputDialog();

		if (headerText.isEmpty()) {
			headerText = "Unos";
		}
		if (title.isEmpty()) {
			title = "Unos";
		}
		if (contentText.isEmpty()) {
			contentText = "Vrijednost";
		}
		if (okText == null || okText.isEmpty()) {
			okText = "Uredu";
		}
		if (cancelText == null || cancelText.isEmpty()) {
			cancelText = "Odustani";
		}

		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);
		dialog.setTitle(title);

		Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
		okButton.setText(okText);
		Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		cancelButton.setText(cancelText);

		dialog.getEditor().setText(currentValue);
		Optional<String> returnedValue = dialog.showAndWait();

		return returnedValue.isEmpty() ? null : returnedValue.get();
	}
}
