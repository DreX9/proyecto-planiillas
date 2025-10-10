package com.jprado.planillas.empresa;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresa(){
        return empresaRepository.findAll();
    }

    public Empresa getEmpresaById(Long id){
        return empresaRepository.findById(id).orElseThrow();
    }


    public Empresa updateAddEmpresa(Empresa empresa){
        
        return empresaRepository.save(empresa);
    }
    
    public void deleteEmpresa(Long id){
        empresaRepository.deleteById(id);
    }
}
