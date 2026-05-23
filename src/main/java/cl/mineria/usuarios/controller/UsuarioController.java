package cl.mineria.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import cl.mineria.usuarios.dto.RequestUsuario;
import cl.mineria.usuarios.mapper.UsuarioMapper;
import cl.mineria.usuarios.model.Usuario;
import cl.mineria.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

   private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }   

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
    
    @PostMapping
    public Usuario saveUsuario(@RequestBody RequestUsuario request) {
        Usuario usuario = UsuarioMapper.toUsuario(request);
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable int id) {
    
        return usuarioService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable int id) {
        usuarioService.deleteUsuario(id);
        return "Usuario con id " + id + " eliminado exitosamente.";
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id,
                    @RequestBody Usuario usuario) {
            return usuarioService.updateUsuario(id, usuario);
    }

}
