package com.jprado.planillas.cargo;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private Long id;
   @NotBlank(message = "El nombre del cargo no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(name = "cargo_nombre", nullable = false, unique = true, length = 50)
    private String nombre;
    @NotNull(message = "El salario base es obligatorio")
    @DecimalMin(value = "0.01", message = "El salario base debe ser mayor que 0")
    @Column(name = "cargo_salario_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioBase;
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Column(name = "cargo_descripcion", length = 255)
    private String descripcion;

}
