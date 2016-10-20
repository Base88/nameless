package de.hermes.model;

import java.util.Date;

public class User {
	
	private String username;
	private String passwort;
	private double	laengengrad;
	private double	breitengrad;
	private String 	bemerkung;
	private Date	zeitpunkt;
	
	//LETS SEE WHAT WILL HAPPEN TODAY!!
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public double getLaengengrad() {
		return laengengrad;
	}
	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}
	public double getBreitengrad() {
		return breitengrad;
	}
	public void setBreitengrad(double breitengrad) {
		this.breitengrad = breitengrad;
	}
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	public Date getZeitpunkt() {
		return zeitpunkt;
	}
	public void setZeitpunkt(Date zeitpunkt) {
		this.zeitpunkt = zeitpunkt;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + passwort + ", laengengrad=" + laengengrad
				+ ", breitengrad=" + breitengrad + ", bemerkung=" + bemerkung + ", zeitpunkt=" + zeitpunkt + "]";
	}
	
	

}
