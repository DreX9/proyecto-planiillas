package com.jprado.planillas.cargo;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("cargo")
@RequiredArgsConstructor
public class CargoController {


    private final CargoService cargoService;

    

    @GetMapping
     public String listaCargo(Model model){
        model.addAttribute("lista", cargoService.getAllCargo());
        model.addAttribute("cargo", new Cargo());
        return "pages/cargo";
    }
    @PostMapping("/save")
    public String addCargo(@Valid @ModelAttribute Cargo cargo, BindingResult error, Model model) {
        if (error.hasErrors()) {
            System.out.println("Errores: " + error.getAllErrors());
            model.addAttribute("cargo", cargoService.getAllCargo());
            model.addAttribute("abrirModal", true);
            return "pages/cargo";
        }
       
        
        cargoService.updateAddCargo(cargo);
        return "redirect:/cargo";
    }
    
    @GetMapping("/edit/{id}")
    public String editCargo(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("cargo", cargoService.getByIdCargo(id));
            model.addAttribute("lista", cargoService.getAllCargo());
            model.addAttribute("abrirModal", true);
            return "pages/cargo";
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "El cargo con ID " + id + " no fue encontrada.");
            return "pages/cargo";
        }
    }
    
    @PostMapping("/delete")
    public String deleteCargo(@RequestParam Long id, Model model) {
        try {
            cargoService.deleteCargo(id);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar el cargo con ID " + id);
        }
        return "redirect:/cargo";
    }
    

}
