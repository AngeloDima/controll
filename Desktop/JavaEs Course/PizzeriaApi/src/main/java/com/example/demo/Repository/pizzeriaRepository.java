package com.example.demo.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.pizzeria;

public interface pizzeriaRepository extends JpaRepository<pizzeria, Integer>{

	 public List<pizzeria> findByNomeLike(String keyword);
	 public List<pizzeria> findByPrezzo(Double prezzo);
	
}



