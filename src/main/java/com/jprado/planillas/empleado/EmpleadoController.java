package com.jprado.planillas.empleado;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jprado.planillas.cargo.CargoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("empleado")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    private final CargoService cargoService;

    @GetMapping
    public String listaEmpleado(Model model){
        model.addAttribute("lista", empleadoService.getAllEmpleado());
        model.addAttribute("cargo", cargoService.getAllCargo());
        model.addAttribute("empleado", new Empleado());
        return "pages/empleado";
    }

    @PostMapping("/save")
    public String addEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult error, Model model){
        if (error.hasErrors()) {
            model.addAttribute("lista", empleadoService.getAllEmpleado());
            model.addAttribute("cargo", cargoService.getAllCargo());
            model.addAttribute("abrirModal", true);
            return "pages/empleado";
        }
        empleadoService.updateAddEmpleado(empleado);
        return "redirect:/empleado";

    }
    @GetMapping("/edit/{id}")
    public String getMethodName(@PathVariable Long id, Model model) {
        try {
             model.addAttribute("empleado", empleadoService.getByIdEmpleado(id));
             model.addAttribute("lista", empleadoService.getAllEmpleado());
            model.addAttribute("cargo", cargoService.getAllCargo());
            model.addAttribute("abrirModal", true);
            return "pages/empleado";
        } catch (NoSuchElementException e) {
             model.addAttribute("error", "El empleado con ID " + id + " no fue encontrada.");
            return "pages/empleado";
        }

    }
    @PostMapping("/delete")
    public String deleteArea(@RequestParam Long id, Model model) {
        try {
            empleadoService.deleteEmpleado(id);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar el empleado con ID " + id);
        }
        return "redirect:/empleado";
    }
    

}
