package cl.duoc.mineria.usuarios.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.mineria.usuarios.dto.UsuarioRequestDTO;
import cl.duoc.mineria.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.mineria.usuarios.model.Usuario;

import java.time.LocalDateTime;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setRut(dto.getRut());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(dto.getRol());
        
        usuario.setFechaRegistro(LocalDateTime.now());

        return usuario;

    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setRut(usuario.getRut());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());

        return dto;
    }
}