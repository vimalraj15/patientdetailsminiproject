package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "patient")
public class Ticket 
{
 
       @Id
       @Column(name = "name")
       private String name;
       
       @Column(name = "gender")
	   private String gender;
	
       @Column(name = "hospital")
       private String  hospital;
       
       @Column(name = "address")
	   private String  address;
	 
       @Column(name = "status")
       private String  status;
       
           
       


	public String getName() {
		return name;
	}


	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getHospital() {
		return hospital;
	}


	public void setHospital(String hospital) {
		this.hospital = hospital;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Ticket(String name, String gender, String hospital, String address, String status) {
		super();
		this.name = name;
		this.gender = gender;
		this.hospital = hospital;
		this.address = address;
		this.status = status;
	}
       

	       
       
	
}
