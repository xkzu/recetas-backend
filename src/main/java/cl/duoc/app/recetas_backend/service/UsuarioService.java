package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);
}
