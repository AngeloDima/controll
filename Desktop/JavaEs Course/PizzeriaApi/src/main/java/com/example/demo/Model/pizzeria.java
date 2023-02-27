package com.example.demo.Model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class pizzeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotEmpty(message = "Devi inserire un nome")
    @Column(nullable = false)
    @Size(min = 4, max = 20, message = "il nome deve contenere minimo 4 caratteri")
    private String nome;

    @NonNull
    @NotEmpty(message = "Devi inserire una descrizione")
    @Column(nullable = false, columnDefinition = "CHAR(10)")
    @Size(min = 5, max = 80, message = "la descrizione deve contenere minimo 5 caratteri")
    private String descrizione;

    @NotNull(message = "Devi inserire un prezzo")
    @DecimalMin(value = "0.01", inclusive = true, message = "Il prezzo non può essere inferiore a 0.01")
    @DecimalMax(value = "999.99", inclusive = true, message = "Il prezzo non può essere inferiore a 999.99")
    private Double prezzo;

    @OneToMany(mappedBy = "pizza")
    private List<Sconto> sconto;
    
    @ManyToMany
    private List <Ingredienti> ingredienti;
    //------------------------------------------------------------

	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public List<Sconto> getSconto() {
		return sconto;
	}

	public void setSconto(List<Sconto> sconto) {
		this.sconto = sconto;
	}

    

}





