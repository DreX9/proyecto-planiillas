package com.jprado.planillas.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerGeneral {
    @GetMapping("/test")
    public String test() {
        return "pages/reportes"; // usa tu mismo HTML
    }

    // @GetMapping("/inicio")
    // public String inicio(){
    //     return "pages/index";
    // }
    // @GetMapping("/usuarios")
    // public String usuarios(){
    //     return "pages/usuarios";
    // }
    // @GetMapping("/empresas")
    // public String proyectos(){
    //     return "pages/empresas";
    // }
    // @GetMapping("/areas")
    // public String areas(){
    //     return "pages/areas";
    // }
    // @GetMapping("/cargos")
    // public String cargos(){
    //     return "pages/cargos";
    // }
    // @GetMapping("/empleados")
    // public String empleados(){
    //     return "pages/empleados";
    // }
    // @GetMapping("/asistencias")
    // public String asistencias(){
    //     return "pages/asistencias";
    // }
    //  @GetMapping("/planillas")
    // public String planillas(){
    //     return "pages/planillas";
    // }
    // @GetMapping("/detallePlanillas")
    // public String detallePlanillas(){
    //     return "pages/detallePlanillas";
    // }
    // @GetMapping("/pagos")
    // public String pagos(){
    //     return "pages/pagos";
    // }
    // @GetMapping("/reportes")
    // public String reportes(){
    //     return "pages/reportes";
    // }
}
