package com.ptf.rs.tiket.util;



import java.util.ArrayList;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.ptf.rs.tiket.model.Status;
import com.ptf.rs.tiket.model.Tiket;

public class SeedData {
	
	private static SeedData singleInstance = null;
	
	private Integer tiketId = 1;

	public ArrayList<Tiket> tiketi;
	
	private SeedData() {
		tiketi = getTiketi();
	}
	
	public static SeedData getInstance() {
		if (singleInstance == null)
            singleInstance = new SeedData();
  
        return singleInstance;
	}
	
	public Integer getNextTiketId() {
		return tiketId++;
	}
	
	private ArrayList<Tiket> getTiketi() {
		
		ArrayList<Tiket> tiketi = new ArrayList<>();

		tiketi.add(new Tiket(
				getNextTiketId(),
				"Pitanje", 
				"Da li je Zemlja ravna ploča?", 
				"Osoba", 
				new Date(), 
				"Email", 
				"Nisko", 
				"Je li Zemlja kugla ili ravna ploča?", 
				"Agent1", 
				"osoba@email.com",
				"061123321",
				"NOVI",
				new Date() ));
			
		return tiketi;
	}
	
	public ArrayList<String> getAgents() {
		ArrayList<String> agents = new ArrayList<>();
		
		agents.add("Agent1");
		agents.add("Agent2");
		agents.add("Agent3");
			
		return agents;
	}
	
	public ArrayList<String> getUsluga() {
		ArrayList<String> usluge = new ArrayList<>();
		
		usluge.add("Pitanje");
		usluge.add("Incident");	
		usluge.add("Problem");
		usluge.add("Zahtjev za promjenom");
			
		return usluge;
	}
	
	public ArrayList<String> getNacin() {
		ArrayList<String> nacin = new ArrayList<>();
		
		nacin.add("Email");
		nacin.add("Telefon");
		nacin.add("Web-stranica");
		
		return nacin;
	}
	
	public ArrayList<String> getPrioritet() {
		ArrayList<String> prioritet = new ArrayList<>();
		
		prioritet.add("Nisko");
		prioritet.add("Srednje");
		prioritet.add("Visoko");
		prioritet.add("Hitno");
		
		return prioritet;
	}

	public ArrayList<String> getStatus() {
		ArrayList<String> status = new ArrayList<>();
		
		status.add("NOVI");
		status.add("OTVOREN");
		status.add("NA_ČEKANJU");
		status.add("RIJEŠEN");
		status.add("ZATVOREN");
		status.add("ČEKA_KUPCA");
		status.add("ČEKA_TREĆU_STRANU");
		
		return status;
	}

}
