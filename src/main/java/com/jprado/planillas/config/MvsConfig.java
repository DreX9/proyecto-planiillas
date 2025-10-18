package com.jprado.planillas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvsConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        registry.addViewController("/pagos").setViewName("/pages/pagos");
        registry.addViewController("/asistencias").setViewName("/pages/asistencias");
        registry.addViewController("/planillas").setViewName("/pages/planillas");
        registry.addViewController("/detallePlanillas").setViewName("/pages/detallePlanillas");
        // registry.addViewController("/reportes").setViewName("/pages/reportes");

    }


}
