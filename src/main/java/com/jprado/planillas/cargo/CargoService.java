package com.jprado.planillas.cargo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    public List<Cargo> getAllCargo(){
        return cargoRepository.findAll();
    }
    public Cargo getByIdCargo(Long id){
        return cargoRepository.findById(id).orElseThrow();
    }
    public Cargo updateAddCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }
    public void deleteCargo(Long id){
        cargoRepository.deleteById(id);
    }
    
}
