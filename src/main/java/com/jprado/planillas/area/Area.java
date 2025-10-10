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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(nullable = false, length = 50, unique = true)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AreaEstado estado;
    //NO DEBE ser nulo este campo lo tenemos bien 
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @NotNull
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    // Nos va a permitir que se ejecute automaticamente antes de que se inserte el registro en la base de datos 
    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDate.now();
    }
}
