package cl.mineria.usuarios.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.mineria.usuarios.model.Usuario;
import cl.mineria.usuarios.repository.UsuarioRepository;
import org.springframework.data.domain.Sort;

@Service
public class UsuarioService {
    @Autowired //ES EL EQUIVALENTE A CREAR UN CONSTRUCTOR
    private UsuarioRepository usuarioRepository;

public List<Usuario> getAllUsuarios() {

    return usuarioRepository.findAll(
        Sort.by(Sort.Direction.ASC, "id"));
    }

    public Usuario getById(int id) { //GET POR ID
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(int id, Usuario usuarioActualizado) {

        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setRut(usuarioActualizado.getRut());
            usuarioExistente.setNombreCompleto(usuarioActualizado.getNombreCompleto());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

}
