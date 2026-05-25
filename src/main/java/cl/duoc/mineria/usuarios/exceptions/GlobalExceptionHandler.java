package cl.duoc.mineria.usuarios.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. CAPTURA DE RECURSOS NO ENCONTRADOS (HTTP 404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalle> manejarResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {
            
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(exception.getMessage())
                .detalles(webRequest.getDescription(false))
                .build();
                
        return new ResponseEntity<>(errorDetalle, HttpStatus.NOT_FOUND);
    }

    // 2. CAPTURA DE ERRORES DE VALIDACIÓN (HTTP 400 - Bean Validation)
    // Se activa automáticamente cuando falla un @NotBlank, @Email, etc., en el Controller
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidaciones(
            MethodArgumentNotValidException exception, WebRequest webRequest) {
            
        Map<String, String> erroresCampos = new HashMap<>();
        
        // Recorremos todos los campos que fallaron y extraemos su mensaje personalizado
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            erroresCampos.put(nombreCampo, mensajeError);
        });

        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Los datos de entrada enviados no son válidos")
                .detalles(erroresCampos.toString()) // Devuelve exactamente qué campos fallaron
                .build();

        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }

    // 3. CAPTURA DE ERRORES GENÉRICOS / INESPERADOS (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalle> manejarGlobalException(
            Exception exception, WebRequest webRequest) {
            
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Ocurrió un error interno en el servidor: " + exception.getMessage())
                .detalles(webRequest.getDescription(false))
                .build();               
        return new ResponseEntity<>(errorDetalle, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}