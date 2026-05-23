package cl.mineria.usuarios.mapper;

import java.time.LocalDateTime;
import cl.mineria.usuarios.dto.RequestUsuario;
import cl.mineria.usuarios.model.Usuario;

public class UsuarioMapper {
    public static Usuario toUsuario(RequestUsuario request) {
            Usuario usuario = new Usuario();
            usuario.setRut(request.rut());
            usuario.setNombreCompleto(request.nombreCompleto());
            usuario.setCorreo(request.correo());
            usuario.setRol(request.rol());
            usuario.setFechaRegistro(LocalDateTime.now());
            return usuario;
        }
    }


