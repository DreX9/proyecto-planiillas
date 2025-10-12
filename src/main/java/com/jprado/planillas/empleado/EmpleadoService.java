package com.jprado.planillas.empleado;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleado(){
        return empleadoRepository.findAll();
    }
    public Empleado getByIdEmpleado(Long id){
        return empleadoRepository.findById(id).orElseThrow();
    }
    public Empleado updateAddEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
    public void deleteEmpleado(Long id){
         empleadoRepository.deleteById(id);
    }
    
}
