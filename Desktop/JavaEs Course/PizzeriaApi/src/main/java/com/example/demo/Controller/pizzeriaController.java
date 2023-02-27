package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.pizzeria;
import com.example.demo.Repository.pizzeriaRepository;

@RestController
@CrossOrigin
@RequestMapping("/")
public class pizzeriaController {

	@Autowired
	pizzeriaRepository repositoryPizza;

	@GetMapping
	public ResponseEntity<List<pizzeria>> index(@RequestParam(name = "filtro", required = false) String filtro) {
		List<pizzeria> result;

		if (filtro != null && !filtro.isEmpty()) {
			result = repositoryPizza.findByNomeLike("%" + filtro + "%");
		} else {
			result = repositoryPizza.findAll();
		}

		if (result.size() > 0) {
			return new ResponseEntity<List<pizzeria>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<pizzeria>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("pizza/{id}")
	public ResponseEntity<pizzeria> detail(@PathVariable("id") Integer id) {

		Optional<pizzeria> res = repositoryPizza.findById(id);
		if (res.isPresent()) {
			return new ResponseEntity<pizzeria>(res.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<pizzeria>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("pizza/create")
	public ResponseEntity<pizzeria> create(@RequestBody pizzeria pizza) {

		pizzeria createdPizza = repositoryPizza.save(pizza);
		if (createdPizza != null) {
			return new ResponseEntity<pizzeria>(createdPizza, HttpStatus.OK);
		} else {
			return new ResponseEntity<pizzeria>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("pizza/{id}")
	public ResponseEntity<pizzeria> paggCreate(@RequestBody pizzeria pizza, @PathVariable("id") Integer id) {

		Optional<pizzeria> result = repositoryPizza.findById(id);

		if (!result.isPresent() || result.get().getId() != pizza.getId()) {
			return new ResponseEntity<pizzeria>(HttpStatus.BAD_REQUEST);
		} else {
			repositoryPizza.save(pizza);
			return new ResponseEntity<pizzeria>(pizza, HttpStatus.OK);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		Optional<pizzeria> pizzaResult = repositoryPizza.findById(id);
		if (pizzaResult.isPresent()) {
			repositoryPizza.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
