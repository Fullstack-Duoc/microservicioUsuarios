package cl.duoc.mineria.usuarios.controller;

import jakarta.validation.Valid; // Necesario para activar Bean Validation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cl.duoc.mineria.usuarios.dto.UsuarioRequestDTO;
import cl.duoc.mineria.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.mineria.usuarios.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    // Aplicamos tu estilo: Inyección por campo limpia con @Autowired, sin constructores ni "final"
    @Autowired
    private UsuarioService usuarioService;

    // 1. OBTENER TODOS LOS USUARIOS
    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // 2. OBTENER UN USUARIO POR ID
    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuarioById(@PathVariable Long id) { // Sincronizado a Long
        return usuarioService.getById(id);
    }

    // 3. CREAR UN NUEVO USUARIO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna explícitamente HTTP 201 Created al tener éxito
    public UsuarioResponseDTO saveUsuario(@Valid @RequestBody UsuarioRequestDTO dto) { 
        // @Valid es obligatorio aquí para que Spring ejecute las restricciones del DTO
        return usuarioService.saveUsuario(dto);
    }

    // 4. ACTUALIZAR UN USUARIO EXISTENTE
    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        // Corregido: Ahora recibe el DTO de entrada validado en lugar de la entidad cruda
        return usuarioService.updateUsuario(id, dto);
    }

    // 5. ELIMINAR UN USUARIO
    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Long id) {

        usuarioService.deleteUsuario(id);

        return "Usuario con id " + id + " eliminado correctamente";
    }

}