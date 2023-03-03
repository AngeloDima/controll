package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.foto;
import com.example.demo.Repository.fotoRepository;

import jakarta.validation.Valid;

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
    
    
    
    
 
    
    @GetMapping("/create")
    public String create(Model model) {
        foto createFoto = new foto();
        model.addAttribute("createFoto", createFoto);
        return "creaFoto";
    }

    @PostMapping("/create")
    public String storeCreate(@ModelAttribute("createFoto") @Valid foto formFoto,BindingResult bindingResult, Model model) {
 {
        

        repository.save(formFoto);
        return "redirect:/";
    }
    
	
    }}



