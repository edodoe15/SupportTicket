package com.ptf.rs.tiket.model;

import java.time.LocalDate;
import java.util.Date;

public class Tiket {

	private Integer id;
	private String tipUsluge;
	private String nazivProblema;
	private String korisnik;
	private Date datumPrijave;
	private String nacinPrijave;
	private String prioritet;
	private String opisProblema;
	private String agent;
	private String email;
	private String telefon;
	private Date datumRjesenja;
	private String status;
	
	public Tiket (Integer Id, String tipUsluge, String nazivProblema, String korisnik, Date datumPrijave, String nacinPrijave,
	String prioritet, String opisProblema, String agent, String email, String telefon, String status, Date datumRjesenja)
	{
		super();
		this.id = Id;
		this.tipUsluge = tipUsluge;
		this.nazivProblema = nazivProblema;
		this.korisnik = korisnik;
		this.datumPrijave = datumPrijave;
		this.nacinPrijave = nacinPrijave;
		this.prioritet = prioritet;
		this.opisProblema = opisProblema;
		this.agent = agent;
		this.email = email;
		this.telefon = telefon;
		this.status = status;
		this.datumRjesenja = datumRjesenja;
	}
	
	public Tiket(String nazivProblema, String tipUsluge, String korisnik, Date datumPrijave, String agent, Date datumRjesenja) 
	{
		this.nazivProblema = nazivProblema;
		this.tipUsluge = tipUsluge;
		this.korisnik = korisnik;
		this.datumPrijave = datumPrijave;
		this.agent = agent;
		this.datumRjesenja = datumRjesenja;
	}

	public String getTipUsluge() {
		return tipUsluge;
	}

	public void setTipUsluge(String tipUsluge) {
		this.tipUsluge = tipUsluge;
	}

	public String getNazivProblema() {
		return nazivProblema;
	}

	public void setNazivProblema(String nazivProblema) {
		this.nazivProblema = nazivProblema;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public String getNacinPrijave() {
		return nacinPrijave;
	}

	public void setNacinPrijave(String nacinPrijave) {
		this.nacinPrijave = nacinPrijave;
	}

	public String getPrioritet() {
		return prioritet;
	}

	public void setPrioritet(String prioritet) {
		this.prioritet = prioritet;
	}

	public String getOpisProblema() {
		return opisProblema;
	}

	public void setOpisProblema(String opisProblema) {
		this.opisProblema = opisProblema;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Date getDatumRjesenja() {
		return datumRjesenja;
	}

	public void setDatumRjesenja(Date datumRjesenja) {
		this.datumRjesenja = datumRjesenja;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
