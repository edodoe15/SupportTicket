package com.ptf.rs.tiket.controllers;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ptf.rs.tiket.TiketFX;
import com.ptf.rs.tiket.dao.TiketDao;
import com.ptf.rs.tiket.model.Tiket;
import com.ptf.rs.tiket.util.SeedData;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TiketController implements Initializable {

	@FXML
	private TableView<Tiket> table;
	@FXML
	private TableColumn<Tiket, String> colProblem;
	@FXML 
	private TableColumn<Tiket, String> colUsluga;
	@FXML
	private TableColumn<Tiket, String> colKorisnik;
	@FXML
	private TableColumn<Tiket, String> colDatumprijave;
	@FXML 
	private TableColumn<Tiket, String> colAgent;
	@FXML 
	private TableColumn<Tiket, String> colDatumrjesenja;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setColumns();
		setRowActions();
		try {
			table.setItems(getData());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<Tiket> data;
		try {
			data = getData();
			table.setItems(data);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			data = getData();
			data.sort((o1, o2) -> {
		        Date date1 = o1.getDatumPrijave();
		        Date date2 = o2.getDatumPrijave();
		        return date2.compareTo(date1);
		    });
		  table.setItems(data);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    

	}
	
	@FXML
	public void dodajNoviTiket(ActionEvent event) throws IOException {
		TiketFX.setRoot("Tiket");
	}

	private void setColumns() {
		colProblem.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("nazivProblema"));
		colUsluga.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("tipUsluge"));
		colKorisnik.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("korisnik"));
		colDatumprijave.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("datumPrijave"));		
		colAgent.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("agent"));
		colDatumrjesenja.setCellValueFactory(
				new PropertyValueFactory<Tiket, String>("datumRjesenja"));	
		
	}
	
	private void setRowActions() {
		table.setRowFactory(tableView -> {
			final TableRow<Tiket> row = new TableRow<>();
			final ContextMenu rowMenu = new ContextMenu();
			MenuItem editItem = new MenuItem("Izmjeni");
			
			editItem.setOnAction(event -> {
				try {
					TiketFX.setRoot("Tiket");
					ManageTiket controller = TiketFX.getLoader().getController();
					controller.loadData(row.getItem().getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	});

			
			rowMenu.getItems().addAll(editItem);
			
			row.contextMenuProperty().bind(
				Bindings.when(row.emptyProperty())
				.then((ContextMenu) null)
				.otherwise(rowMenu));
			
			return row;
		});
	}
	
	private ObservableList<Tiket> getData() throws ClassNotFoundException, SQLException {
		return FXCollections.observableArrayList(TiketDao.getTickets());
	}
	
}
