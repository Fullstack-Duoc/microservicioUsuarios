package cl.duoc.mineria.usuarios.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Ordena los resultados en postman
@JsonPropertyOrder({
    "id",
    "rut",
    "nombreCompleto",
    "correo",
    "rol",
    "fechaRegistro"
})

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DECORADOR AUTOINCREMENTAL DEL ID 
    @Column(name = "id")
    private Long id;

    @Column(name = "rut", nullable = false, length = 12)
    private String rut;

    @Column(name = "nombre_completo", nullable = false, length = 200)
    private String nombreCompleto;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "rol", nullable = false, length = 30)
    private String rol;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}
