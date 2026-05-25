package cl.duoc.mineria.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mineria.usuarios.config.WebClientConfig;
import cl.duoc.mineria.usuarios.dto.UsuarioRequestDTO;
import cl.duoc.mineria.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.mineria.usuarios.exceptions.ResourceNotFoundException;
import cl.duoc.mineria.usuarios.mapper.UsuarioMapper;
import cl.duoc.mineria.usuarios.model.Usuario;
import cl.duoc.mineria.usuarios.repository.UsuarioRepository;

import org.springframework.data.domain.Sort;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired //ES EL EQUIVALENTE A CREAR UN CONSTRUCTOR
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private WebClientConfig WebClient;

    // 1. OBTENER TODOS LOS USUARIOS
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(usuarioMapper::toResponseDTO)
                .toList();
    }

    // 2. OBTENER POR ID
    public UsuarioResponseDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));
        return usuarioMapper.toResponseDTO(usuario);
    }

    // 3. GUARDAR NUEVO USUARIO
    public UsuarioResponseDTO saveUsuario(UsuarioRequestDTO dto) {
        // Si el RUT ya existe en la base de datos, frenamos la inserción de inmediato
        if (usuarioRepository.findByRut(dto.getRut()).isPresent()) {
            throw new IllegalArgumentException("El número de RUT '" + dto.getRut() + "' ya se encuentra registrado en el sistema.");
        }
        
        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuarioGuardado);
    }

    // 4. ELIMINAR USUARIO
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Usuario no encontrado con el ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // 5. ACTUALIZAR USUARIO
    public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Usuario no encontrado con el ID: " + id));

        usuarioExistente.setRut(dto.getRut());
        usuarioExistente.setNombreCompleto(dto.getNombreCompleto());
        usuarioExistente.setCorreo(dto.getCorreo());
        usuarioExistente.setRol(dto.getRol());

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toResponseDTO(usuarioActualizado);
    }

}
