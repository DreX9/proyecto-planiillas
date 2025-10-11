package com.jprado.planillas.area;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.jprado.planillas.empresa.EmpresaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;
    private final EmpresaService empresaService;
    
    @GetMapping
    public String listaAreas(Model model) {
        model.addAttribute("lista", areaService.getAllArea());
        model.addAttribute("empresa", empresaService.getAllEmpresa());
        model.addAttribute("area", new Area());
        return "pages/area";
    }
    @PostMapping("/save")
    public String addArea(@Valid @ModelAttribute Area area, BindingResult error, Model model) {
        if (error.hasErrors()) {
            System.out.println("Errores: " + error.getAllErrors());
            model.addAttribute("lista", areaService.getAllArea());
            model.addAttribute("empresa", empresaService.getAllEmpresa());
            model.addAttribute("abrirModal", true);
            return "pages/area";
        }
       
        
        areaService.updateAddArea(area);
        return "redirect:/area";
    }
    
    @GetMapping("/edit/{id}")
    public String editEmpresa(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("area", areaService.getByIdArea(id));
            model.addAttribute("empresa", empresaService.getAllEmpresa());
            model.addAttribute("lista", areaService.getAllArea());
            model.addAttribute("abrirModal", true);
            return "pages/area";
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "La empresa con ID " + id + " no fue encontrada.");
            return "pages/area";
        }
    }
    
    @PostMapping("/delete")
    public String deleteArea(@RequestParam Long id, Model model) {
        try {
            areaService.deleteArea(id);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar la empresa con ID " + id);
        }
        return "redirect:/area";
    }
    
    
}
