package com.jprado.planillas.area;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;
    public List<Area> getAllArea(){
        return areaRepository.findAll();
    }
    public Area getByIdArea (Long id){
        return areaRepository.findById(id).orElseThrow();
    }
    public Area updateAddArea(Area area){
        return areaRepository.save(area);
    }
    public void deleteArea(Long id){
         areaRepository.deleteById(id);
    }
    public List<Area> getAreasByEmpresa(Long empresaId) {
        return areaRepository.findByEmpresaId(empresaId);
    }
}
