package com.example.demo.Model;

import java.sql.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Sconto {
    
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String Offerta;
   
    private Date inizio;
    
    private Date fine;

	//------------------------------------------------------------
    @ManyToOne
    
    private pizzeria pizza;
    //------------------------------------------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOfferta() {
		return Offerta;
	}

	public void setOfferta(String offerta) {
		Offerta = offerta;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public pizzeria getPizza() {
		return pizza;
	}

	public void setPizza(pizzeria pizza) {
		this.pizza = pizza;
	}


}



