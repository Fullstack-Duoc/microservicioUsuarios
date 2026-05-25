package cl.duoc.mineria.usuarios.exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetalle {

    private LocalDateTime timestamp; // Fecha y hora exacta del error
    private String mensaje;          // Resumen amigable del error
    private String detalles;         // Ruta o contexto donde ocurrió el fallo
}