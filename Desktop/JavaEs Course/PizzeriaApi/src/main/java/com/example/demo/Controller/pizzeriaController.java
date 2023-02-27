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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Ingredienti;
import com.example.demo.Model.pizzeria;
import com.example.demo.Repository.ingredientiRepository;
import com.example.demo.Repository.pizzeriaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class pizzeriaController {

    @Autowired
    pizzeriaRepository repository;
    
    
    
    @Autowired
    ingredientiRepository repositoryIngredienti;

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "prezzo", required = false) Double prezzo, Model model) {
        List<pizzeria> Pizza;

        if (keyword != null && !keyword.isEmpty()) {
        	Pizza = repository.findByNomeLike("%" + keyword + "%");
        } else if (prezzo != null) {
        	Pizza = repository.findByPrezzo(prezzo);
        } else {
        	Pizza = repository.findAll();
        }

        model.addAttribute("Pizza", Pizza);
        return "home";
    }
    
    
    @GetMapping("pizza/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
    	pizzeria dettPizza = repository.getReferenceById(id);  
    	model.addAttribute("dettPizza", dettPizza);  	
    	return "pizza";
    	
    }
    
    
    //crea
    
    @GetMapping("/create")
    public String create(Model model) {
        pizzeria newPizza = new pizzeria();
        model.addAttribute("newPizza", newPizza);
        return "create";
    }

    @PostMapping("/create")
    public String storeCreate(@Valid @ModelAttribute("newPizza") pizzeria formPizzeria,BindingResult bindingResult, Model model) {
        
        	if (bindingResult.hasErrors()) {
        		return "create";
        	}
        	repository.save(formPizzeria);
        return "redirect:/";
    }



    //edit
    
    
    @GetMapping("/edit/{id}") 
    public String storeEdit(@PathVariable("id") Integer id, Model model) {
        pizzeria editPizza;
        editPizza = repository.getReferenceById(id);
        
        
        List<Ingredienti> editIngredienti = repositoryIngredienti.findAll();
        
        
        
        
        model.addAttribute("editIngredienti", editIngredienti);
        model.addAttribute("editPizza", editPizza);
        return "edit";
        
        
    }

    
    @PostMapping("/edit/{id}")
    public String update(
    		@Valid @ModelAttribute ("editPizza") pizzeria formPizzeria,BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		return "edit";
    	}
    		repository.save(formPizzeria);
    		return "redirect:/";
    }
    
    

    //delete
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
    
    




