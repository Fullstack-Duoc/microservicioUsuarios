package cl.duoc.mineria.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El RUT es obligatorio y no puede estar vacío")
    private String rut;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto; // Corregido para sincronizarse con la Entidad y sanar el error del Mapper

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo debe tener un formato válido (ej: usuario@dominio.com)")
    private String correo;

    @NotBlank(message = "El rol operativo es obligatorio (ej: OPERADOR, ADMINISTRADOR)")
    private String rol;
}