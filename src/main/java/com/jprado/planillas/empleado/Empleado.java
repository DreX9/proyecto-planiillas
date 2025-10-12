package com.jprado.planillas.empleado;

import java.time.LocalDate;

import com.jprado.planillas.area.Area;
import com.jprado.planillas.cargo.Cargo;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long id;
    @NotBlank(message = "El nombre del empleado no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(name = "empleado_nombre", nullable = false, length = 50)
    private String nombre;
    @NotBlank(message = "El apellido del empleado no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
    @Column(name = "empleado_apellido", nullable = false, length = 50)
    private String apellido;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener exactamente 9 dígitos")
    @Column(nullable = false, name = "empleado_telefono", length = 9)
    private String telefono;
    @NotBlank(message = "El correo del empleado no puede estar vacío")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    @Column(name = "empleado_correo", nullable = false, length = 100, unique = true)
    private String correo;
    @NotNull(message = "La fecha de contrato es obligatoria")
    @Column(name = "empleado_fecha_contrato", nullable = false)
    private LocalDate fechaContrato = LocalDate.now();
    @NotNull(message = "El estado del empleado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "empleado_estado", nullable = false, length = 15)
    private EmpleadoEstado estado;
    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Size(max = 20, message = "El tipo de documento no puede superar los 20 caracteres")
    @Column(name = "empleado_tipo_documento", nullable = false, length = 20)
    private String tipoDocumento;
    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(max = 20, message = "El DNI no puede superar los 20 caracteres")
    @Column(name = "empleado_dni", nullable = false, length = 20, unique = true)
    private String dni;
    @NotBlank(message = "La dirección es un campo obligatorio")
    @Column(nullable = false, name = "empleado_direccion")
    private String direccion;
    @NotNull(message = "El cargo es obligatorio")
    @ManyToOne
    @JoinColumn(name = "empleado_id_cargo", nullable = false)
    private Cargo cargo;
    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;
    @PrePersist
    public void prePersist() {
        if (fechaContrato == null) {
            fechaContrato = LocalDate.now();
        }
    }
}
