package cl.duoc.mineria.usuarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Configura de forma nativa que esta excepción representa un recurso no encontrado
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {   
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}