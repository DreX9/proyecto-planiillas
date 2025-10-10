package com.jprado.planillas.empresa;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaService empresaService;
    @GetMapping
    public String list(Model model) {
        List<Empresa> listar= empresaService.getAllEmpresa();
        if (listar.isEmpty()) {
             model.addAttribute("mensaje", "No ahi datos para mostrar");
        } 
        model.addAttribute("lista", listar);
        model.addAttribute("empresa", new Empresa());
        return "pages/empresa";
         
    }

    @GetMapping("/edit/{id}")
    public String editEmpresa(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("empresa", empresaService.getEmpresaById(id));
            model.addAttribute("lista", empresaService.getAllEmpresa());
            model.addAttribute("abrirModal", true);
            return "pages/empresa";
        } catch (NoSuchElementException  e) {
            model.addAttribute("error", "La empresa con ID " + id + " no fue encontrada.");
            return "pages/empresa";
        }
    }
    @PostMapping("/save")
    public String addUpdateEmpresa(@Valid @ModelAttribute Empresa empresa, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("lista", empresaService.getAllEmpresa());
            model.addAttribute("abrirModal", true);
            return "pages/empresa";
        }
        empresaService.updateAddEmpresa(empresa);
        
        return "redirect:/empresa";
        
    }
    
    @PostMapping("/delete")
    public String deleteEmpresa(@RequestParam  Long id, Model model) {
        try {
            empresaService.deleteEmpresa(id);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar la empresa con ID " + id);
        }
        return "redirect:/empresa";
    }
    
    
}
