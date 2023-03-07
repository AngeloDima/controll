package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String index(Model model) {
        List<foto> elencoFoto = repository.findAll();
        model.addAttribute("elencoFoto", elencoFoto);
        return "home";
    }

    @GetMapping("foto/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        foto dettFoto = repository.findById(id).orElse(null);
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
    public String storeCreate(@ModelAttribute("createFoto") @Valid foto formFoto, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "creaFoto";
        }
        
        // Controllo autorizzazioni
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/";
        }
        
        // Aggiungi manualmente il ruolo "ADMIN" all'utente autorizzato
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        
        repository.save(formFoto);
        return "redirect:/";
    }




    
    
    
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
    	
        foto editFoto;
        editFoto = repository.getReferenceById(id);
        model.addAttribute("editFoto", editFoto);
        
        return "edit";
    }

    
    
    
    
    @PostMapping("/edit/{id}")
    public String paggEdit(@ModelAttribute("editFoto") @Valid foto formFoto, BindingResult bindingResult, Model model, @PathVariable("id") Integer id) {

        // Controllo autorizzazioni
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/";
        }
        
        // Aggiungi manualmente il ruolo "ADMIN" all'utente autorizzato
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        foto existingFoto = repository.findById(id).orElse(null);
        if (existingFoto == null) {
            return "redirect:/";
        }
        existingFoto.setTitolo(formFoto.getTitolo());
        existingFoto.setDescrizione(formFoto.getDescrizione());
        existingFoto.setUrl(formFoto.getUrl());
        repository.save(existingFoto);
        return "redirect:/";
    }

    
    
    
    
    
    
    
    //delete
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        if (isAdmin) {
            repository.deleteById(id);
        }
        return "redirect:/";
    }

}
    
    
    







