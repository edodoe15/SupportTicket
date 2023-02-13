package com.ptf.rs.tiket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import com.ptf.rs.tiket.database.TiketDatabase;
import com.ptf.rs.tiket.model.Status;
import com.ptf.rs.tiket.model.Tiket;
import com.ptf.rs.tiket.util.DateConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TiketDao {
  //nazivProblema, tipUsluge, korisnik, datumPrijave, agent, datumRjesenja
	public static ObservableList<Tiket> getTickets() throws SQLException, ClassNotFoundException {
		String selectStatement = "SELECT * FROM Tiket";

		try {
			ResultSet resultSetTickets = TiketDatabase.dbExecuteQuery(selectStatement);
			ObservableList<Tiket> ticketList = getTicketsList(resultSetTickets);
			return ticketList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			throw e;
		}
	}	

	public static Tiket getTicket(Integer ticketId) throws SQLException, ClassNotFoundException {
		String selectStatement = "SELECT * FROM Tiket WHERE Tiket.id=" + ticketId;

		try {
			ResultSet resultSetTicket = TiketDatabase.dbExecuteQuery(selectStatement);

			Tiket tiket = getTicketFromResultSet(resultSetTicket);

			return tiket;
		} catch (SQLException e) {
			System.out.println("While searching a ticket with " + ticketId + " id, an error occurred: " + e);

			throw e;
		}
	}

	
	public static void addTicket(String tipUsluge, String nazivProblema, String korisnik, Date datumPrijave, String nacinPrijave,
			String prioritet, String opisProblema, String agent, String email, String telefon, String vrsta, Date datumRjesenja) throws SQLException, ClassNotFoundException {
		String insertStatement = "INSERT INTO Tiket\n"
				+ "(tipUsluge, nazivProblema, korisnik, datumPrijave, nacinPrijave, prioritet, opisProblema, agent, email, telefon, vrsta, datumRjesenja)\n"
				+ "VALUES\n" + "('" + tipUsluge + "', " + " '" + nazivProblema + "', " + " '" + korisnik + "', " + " '" + DateConverter.toSqlDate(datumPrijave)
				+ "', " + " '" + nacinPrijave + "', " + " '" + prioritet + "', " + " '" + opisProblema + "', " + " '" + agent + "', " + " '" + email + "', " + " '" + telefon + 
				"', " + " '" + vrsta + "', " + null +  ")";

		try {
			TiketDatabase.dbExecuteUpdate(insertStatement);
		} catch (SQLException e) {
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}

	
	public static void updateTicket(Integer ticketId, String agent, String vrsta)
			throws SQLException, ClassNotFoundException {

		String updateStatement = "UPDATE Tiket SET agent = '" + agent + "', vrsta = '" + vrsta + "' WHERE id = " + ticketId + ";";

		try {
			TiketDatabase.dbExecuteUpdate(updateStatement);
		} catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	public static void updateDatumRjesenja(Integer ticketId) throws SQLException, ClassNotFoundException {
		
		Date datumRjesenja = new Date();
		String updateStatement = "UPDATE Tiket SET datumRjesenja = '" + DateConverter.toSqlDate(datumRjesenja) + "' WHERE id = " + ticketId + ";";
		try {
			TiketDatabase.dbExecuteUpdate(updateStatement);
		} catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}

    /*
	public static void deleteMember(Integer memberId) throws SQLException, ClassNotFoundException {
		String deleteStatement = "DELETE FROM members\n" + "WHERE id =" + memberId + ";";

		try {
			GymDatabase.dbExecuteUpdate(deleteStatement);
		} catch (SQLException e) {
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}*/
	
	/*Status.valueOf(rs.getString("status"))*/
	

	/*
	 * rs.getString("nazivProblema"),
					rs.getString("tipUsluge"), 
					rs.getString("korisnik"), 
					rs.getDate("datumPrijave"), 
					rs.getString("agent"),
					rs.getDate("datumRjesenja")*/
	private static ObservableList<Tiket> getTicketsList (ResultSet rs) throws SQLException, ClassNotFoundException {
		ObservableList<Tiket> tiketList = FXCollections.observableArrayList();

		while (rs.next()) {
			Tiket tiket = new Tiket(
					rs.getInt("id"), 
					rs.getString("tipUsluge"), 
					rs.getString("nazivProblema"),
					rs.getString("korisnik"), 
					rs.getDate("datumPrijave"), 
					rs.getString("nacinPrijave"), 
					rs.getString("prioritet"),
					rs.getString("opisProblema"), 
					rs.getString("agent"),
					rs.getString("email"),
					rs.getString("telefon"), 
					rs.getString("vrsta"),
					rs.getDate("datumRjesenja")
					);

			tiketList.add(tiket);
		}
		return tiketList;
	}

	private static Tiket getTicketFromResultSet(ResultSet rs) throws SQLException {
		
		Tiket tiket = null;
		if (rs.next()) {
		tiket = new Tiket(
					rs.getInt("id"), 
					rs.getString("tipUsluge"), 
					rs.getString("nazivProblema"),
					rs.getString("korisnik"), 
					rs.getDate("datumPrijave"), 
					rs.getString("nacinPrijave"), 
					rs.getString("prioritet"),
					rs.getString("opisProblema"), 
					rs.getString("agent"),
					rs.getString("email"),
					rs.getString("telefon"), 
					rs.getString("vrsta"),
					rs.getDate("datumRjesenja")
						);
		}
		return tiket;
	}
	
}
