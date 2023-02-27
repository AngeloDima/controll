package com.example.demo.Model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Ingredienti {

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<pizzeria> getPizza() {
		return Pizza;
	}

	public void setPizza(List<pizzeria> pizza) {
		Pizza = pizza;
	}

	@ManyToMany(mappedBy = "ingredienti")
	private List <pizzeria> Pizza;
}




