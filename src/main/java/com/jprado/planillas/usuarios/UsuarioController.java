package com.jprado.planillas.usuarios;

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

import com.jprado.planillas.area.AreaService;
import com.jprado.planillas.empleado.EmpleadoService;
import com.jprado.planillas.empresa.Empresa;
import com.jprado.planillas.empresa.EmpresaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AreaService areaService;
    private final EmpresaService empresaService;
    private final EmpleadoService empleadoService;

    @GetMapping
    public String listaUsuario(Model model) {
        model.addAttribute("lista", usuarioService.getAllUsuario());
        model.addAttribute("area", areaService.getAllArea());
        model.addAttribute("empresa", empresaService.getAllEmpresa());
        model.addAttribute("empleado", empleadoService.getAllEmpleado());
        model.addAttribute("usuario", new Usuario());
        return "pages/usuario";
    }

    @PostMapping("/save")
    public String addUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("lista", usuarioService.getAllUsuario());
            model.addAttribute("area", areaService.getAllArea());
            model.addAttribute("empresa", empresaService.getAllEmpresa());
            model.addAttribute("empleado", empleadoService.getAllEmpleado());
            model.addAttribute("abrirModal", true);
            return "pages/usuario";
        }
        usuarioService.updateAddUsuario(usuario);

        return "redirect:/usuario";

    }

    @GetMapping("/edit/{id}")
    public String editUsuario(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("usuario", usuarioService.getByIdUsuario(id));
            model.addAttribute("lista", usuarioService.getAllUsuario());
            model.addAttribute("area", areaService.getAllArea());
            model.addAttribute("empresa", empresaService.getAllEmpresa());
            model.addAttribute("empleado", empleadoService.getAllEmpleado());
            model.addAttribute("abrirModal", true);
            return "pages/usuario";
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "La empresa con ID " + id + " no fue encontrada.");
            return "pages/usuario";
        }
    }

    @PostMapping("/delete")
    public String deleteUsuario(@RequestParam Long id, Model model) {
        try {
            usuarioService.deleteUsuario(id);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar la empresa con ID " + id);
        }
        return "redirect:/usuario";
    }

}
