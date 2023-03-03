package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.foto;
import com.example.demo.Repository.fotoRepository;

@Controller
@RequestMapping("/")
public class fotoController {

	
	@Autowired
	
	fotoRepository repository;
	
	@GetMapping
	public String index (Model model) {
		List<foto> elencoFoto;
		elencoFoto = repository.findAll();
		model.addAttribute("elencoFoto", elencoFoto);
		return "home";
	}
	
	
    @GetMapping("foto/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
    	foto dettFoto = repository.getReferenceById(id);
    	model.addAttribute("dettFoto", dettFoto);
    	return "dettagli";
    	
    }
	
}
