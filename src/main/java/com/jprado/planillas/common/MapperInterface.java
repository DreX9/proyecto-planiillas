package com.jprado.planillas.common;

public interface MapperInterface <E, DW, DV>{
    DV toDto(E entity);
    E toEntity(DW dto);
}
