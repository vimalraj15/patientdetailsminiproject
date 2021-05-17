package com.example.demo.model;

public class MainTable {

	private String cases;
	private String deaths;
	private String recovered;
	private String treatment;
	
	public MainTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MainTable(String cases, String deaths, String recovered, String treatment) {
		super();
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.treatment = treatment;
	}

	public String getCases() {
		return cases;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	


	
}
