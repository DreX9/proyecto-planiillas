package com.jprado.planillas.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long id;
    @NotBlank(message = "El nombre de la empresa es un campo obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false, name = "empresa_nombre", length = 100, unique = true)
    private String nombre;
    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe contener exactamente 11 dígitos numéricos")
    @Column(nullable = false, name = "empresa_ruc", length = 11, unique = true)
    private String ruc;
    @NotBlank(message = "La razón social es un campo obligatorio")
    @Column(nullable = false, name = "empresa_razon")
    private String razonSocial;
    @NotBlank(message = "El teléfono principal es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener exactamente 9 dígitos")
    @Column(nullable = false, name = "empresa_telefono", length = 9)
    private String telefono;
    @NotBlank(message = "El teléfono de respaldo es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El teléfono de respaldo debe tener exactamente 9 dígitos")
    @Column(nullable = false, name = "empresa_telefono_respaldo", length = 9)
    private String telefonoRespaldo;
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Column(nullable = false, name = "empresa_correo")
    private String correo;
    @NotBlank(message = "La dirección es un campo obligatorio")
    @Column(nullable = false, name = "empresa_direccion")
    private String direccion;

}
