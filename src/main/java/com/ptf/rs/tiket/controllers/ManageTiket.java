package com.ptf.rs.tiket.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.ptf.rs.tiket.TiketFX;
import com.ptf.rs.tiket.alerts.CustomAlert;
import com.ptf.rs.tiket.dao.TiketDao;
import com.ptf.rs.tiket.model.Status;
import com.ptf.rs.tiket.model.Tiket;
import com.ptf.rs.tiket.util.DateConverter;
import com.ptf.rs.tiket.util.SeedData;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ManageTiket implements Initializable {
	
	@FXML 
	private ComboBox<String> txtUsluga;
	
	@FXML
	private TextField txtNazivProblema;
	
	@FXML
	private TextField txtKorisnik;
	
    @FXML
    private TextField txtOpisProbelma;
    
    @FXML
    private DatePicker txtDatumPrijave;
    
    @FXML
    private ComboBox<String> cbNacinPrijave;
    
    @FXML
    private ComboBox<String> cbPrioritet;
    
    @FXML
    private ComboBox<String> cbAgent;
    
    @FXML
    private ComboBox<String> cbStatus;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtTelefon;
    
    @FXML
    private Label uslugaError;
    
    @FXML
    private Label problemError;
    
    @FXML 
    private Label korisnikError;
    
    @FXML
    private Label dprijaveError;
    
    @FXML
    private Label nprijaveError;
    
    @FXML
    private Label prioritetError;
    
    @FXML 
    private Label agentError;
    
    @FXML
    private Label opisError;
    
    @FXML
    private Label emailtelError;
    
    private Tiket tiketTiket;
    
	
	public void loadData(Tiket tiket) {
		txtUsluga.setValue(tiket.getTipUsluge());
		txtUsluga.setDisable(true);
		txtNazivProblema.setText(tiket.getNazivProblema());
		txtNazivProblema.setDisable(true);
		txtKorisnik.setText(tiket.getKorisnik());
		txtKorisnik.setDisable(true);
		txtDatumPrijave.setValue(DateConverter.toLocalDate(tiket.getDatumPrijave()));
		txtDatumPrijave.setDisable(true);
		txtOpisProbelma.setText(tiket.getOpisProblema());
		txtOpisProbelma.setDisable(true);
		cbNacinPrijave.setValue(tiket.getNacinPrijave());
		cbNacinPrijave.setDisable(true);
		cbPrioritet.setValue(tiket.getPrioritet());
		cbPrioritet.setDisable(true);
		cbAgent.setValue(tiket.getAgent());
		txtEmail.setText(tiket.getEmail());
		txtEmail.setDisable(true);
		txtTelefon.setText(tiket.getTelefon());
		txtTelefon.setDisable(true);
		cbStatus.setDisable(false);
		cbStatus.setItems(FXCollections.observableArrayList(SeedData.getInstance().getStatus()));
		tiketTiket = tiket;
	}
	
	public void loadData(Integer ticketId) throws ClassNotFoundException, SQLException, IOException {
		Tiket tiket = TiketDao.getTicket(ticketId);
		if (tiket == null) {
			CustomAlert.show("Nema tiketa", "Tiket", "Tiket sa nazivom problema " + ticketId + " ne postoji.", AlertType.INFORMATION);
			TiketFX.setRoot("Main");
			return;
		}
		loadData(tiket);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		uslugaError.setVisible(false);
		problemError.setVisible(false);
		korisnikError.setVisible(false);
		dprijaveError.setVisible(false);
		nprijaveError.setVisible(false);
		prioritetError.setVisible(false);
		agentError.setVisible(false);
		opisError.setVisible(false);
		emailtelError.setVisible(false);
		
		txtUsluga.setItems(FXCollections.observableArrayList(SeedData.getInstance().getUsluga()));
		cbNacinPrijave.setItems(FXCollections.observableArrayList(SeedData.getInstance().getNacin()));
		cbPrioritet.setItems(FXCollections.observableArrayList(SeedData.getInstance().getPrioritet()));
		cbAgent.setItems(FXCollections.observableArrayList(SeedData.getInstance().getAgents()));
		cbStatus.setValue("NOVI");
		cbStatus.setDisable(true);
		
		setMinAndMaxDate();
		
	}
	
	public void goBackAction(ActionEvent event) throws IOException {	
		TiketFX.setRoot("Main");
	}
	
	public void spasiNoviTiket(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(!isValid()) {
			return;
		}
		

		if (tiketTiket != null) {

			TiketDao.updateTicket(tiketTiket.getId(), cbAgent.getValue(), cbStatus.getValue());
			
			String status = cbStatus.getValue();
			if(status.equals("OTVOREN"))
			{
				TiketDao.updateDatumRjesenja(tiketTiket.getId());
			}

			CustomAlert.show("Izmjena tiketa", "Promjena podataka", "Tiket je uspješno izmjenjen.",
					AlertType.INFORMATION);
			
			return;
		}
		
		
		
		TiketDao.addTicket(txtUsluga.getValue(), txtNazivProblema.getText(), txtKorisnik.getText(), DateConverter.toDate(txtDatumPrijave.getValue()),
				cbNacinPrijave.getValue(), cbPrioritet.getValue(), txtOpisProbelma.getText(), cbAgent.getValue(),
				 txtEmail.getText(), txtTelefon.getText(), cbStatus.getValue(), null);

		Optional<ButtonType> result = CustomAlert.showConfirmation("Novi tiket", "Dodavanje novog tiketa",
				"Da li želite dodati još jedan tiket? U suprotnom bit ćete vraćeni na prozor sa podacima o tiketima.",
				"Da želim", "Ne");			
		
		
		if(result.get() == ButtonType.OK) {
			txtUsluga.setValue(null);
			txtNazivProblema.setText(null);
			txtKorisnik.setText(null);
			txtDatumPrijave.setValue(null);
			cbNacinPrijave.setValue(null);
			cbPrioritet.setValue(null);
			txtOpisProbelma.setText(null);
			cbAgent.setValue(null);	
			txtEmail.setText(null);
			txtTelefon.setText(null);
			txtNazivProblema.requestFocus();
		}
		
		if(result.get() == ButtonType.CANCEL) {
			try {
				TiketFX.setRoot("Main");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void setMinAndMaxDate() {
		LocalDate currentDate = LocalDate.now();
		LocalDate minDate = DateConverter.addYears(currentDate, -100);
		LocalDate maxDate = LocalDate.now();
		
		txtDatumPrijave.setValue(maxDate);
		txtDatumPrijave.setDayCellFactory(d -> new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
			}
		});
	}
	
	private boolean isValid() {
		boolean isValid = true;
		uslugaError.setVisible(false);
		problemError.setVisible(false);
		korisnikError.setVisible(false);
		dprijaveError.setVisible(false);
		nprijaveError.setVisible(false);
		prioritetError.setVisible(false);
		agentError.setVisible(false);
		opisError.setVisible(false);
		emailtelError.setVisible(false);
		
		if(txtUsluga.getValue() == null) {
			uslugaError.setVisible(true);
			isValid = false;
		}
		
		if(txtNazivProblema.getText().isEmpty() || txtNazivProblema.getText().length() > 500) {
			problemError.setVisible(true);
			isValid = false;
		}
		
		if(txtKorisnik.getText().isEmpty() || txtKorisnik.getText().length() > 100) {
			korisnikError.setVisible(true);
			isValid = false;
		}
		if(txtDatumPrijave.getValue() == null) {
			dprijaveError.setVisible(true);
			isValid = false;
		}
		if(cbNacinPrijave.getValue() == null) {
			nprijaveError.setVisible(true);
			isValid = false;
		}
		if(cbPrioritet.getValue() == null) {
			prioritetError.setVisible(true);
			isValid = false;
		}
		if(cbAgent.getValue() == null) {
			agentError.setVisible(true);
			isValid = false;
		}
		if(txtOpisProbelma.getText().isEmpty() || txtOpisProbelma.getText().length() > 500) {
			opisError.setVisible(true);
			isValid = false;
		}
		if(txtTelefon.getText().isEmpty() && txtEmail.getText().isEmpty()){
			emailtelError.setVisible(true);
			isValid = false;
		}
		
			
		return isValid;
	}


}
