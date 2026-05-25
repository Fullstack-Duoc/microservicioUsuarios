package cl.duoc.mineria.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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

    @Pattern(
        regexp = "^[0-9]{1,2}\\.[0-9]{3}\\.[0-9]{3}-[0-9kK]{1}$",
        message = "Formato de RUT inválido. Ejemplo válido: 18.123.456-7"
    )

    private String rut;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "El correo electrónico es obligatorio")

    @Email(message = "El correo debe tener un formato válido (ej: usuario@dominio.com)")
    private String correo;

    @NotBlank(message = "El rol operativo es obligatorio (ej: OPERADOR, ADMINISTRADOR)")
    private String rol;
}