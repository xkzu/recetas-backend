package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario register(Usuario user);

    Optional<Usuario> findByNombreUsuario(String nombre);
}
