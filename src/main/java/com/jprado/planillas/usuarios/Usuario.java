package com.jprado.planillas.usuarios;

import com.jprado.planillas.empleado.Empleado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_name", nullable = false)
    @NotBlank(message = "El nombre de usuario es un caracter obligatorio")
    private String username;
    @Column(name = "usuario_password", nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    @OneToOne
    @JoinColumn(name = "usuario_empleado_id")
    private Empleado empleado;

}
