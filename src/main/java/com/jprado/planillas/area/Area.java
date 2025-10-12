package com.jprado.planillas.area;

import java.time.LocalDate;

import com.jprado.planillas.empresa.Empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;
    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(name = "area_nombre", nullable = false, length = 50, unique = true)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "area_estado", nullable = false, length = 10)
    private AreaEstado estado;
    //NO DEBE ser nulo este campo lo tenemos bien 
    @ManyToOne
    @JoinColumn(name = "area_id_empresa")
    private Empresa empresa;
    //Le asigno la fecha actual para poder llamarlo en el thymeleaf con el dato completo 
    @Column(name = "area_fecha_creacion", nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();
    // Nos va a permitir que se ejecute automaticamente antes de que se inserte el registro en la base de datos 
    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDate.now();
        }
    }
}
