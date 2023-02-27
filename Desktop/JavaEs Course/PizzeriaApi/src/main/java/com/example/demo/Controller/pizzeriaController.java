package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.pizzeria;
import com.example.demo.Repository.pizzeriaRepository;

@RestController
@CrossOrigin
@RequestMapping("/pizze")
public class pizzeriaController {

	@Autowired
	pizzeriaRepository repositoryPizza;
	
	
	
	
	@GetMapping
	public List <pizzeria> index(){
		
		
		return repositoryPizza.findAll();
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity <pizzeria> detail(@PathVariable ("id") Integer id) {
		
		Optional<pizzeria> res=repositoryPizza.findById(id);
		if (res.isPresent()) {
			return new ResponseEntity<pizzeria>(res.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity <pizzeria> (HttpStatus.NOT_FOUND);
		}
		 
	}
	
	@PostMapping()
	public pizzeria  create (@RequestBody pizzeria pizza) {

		return repositoryPizza.save(pizza);
	}
	
	
	
	@PutMapping("{id}") 
	public pizzeria  PaggCreate (@RequestBody pizzeria pizza, @PathVariable("id") Integer id) {

		pizzeria p= repositoryPizza.getReferenceById(id);
		
		p.setNome(p.getNome());
		return repositoryPizza.save(p);
	}
	
	
	
	
	@DeleteMapping("{id}") 
	public void delete (@PathVariable ("id") Integer id) {
		
		repositoryPizza.deleteById(id);
	}
}
    
    




