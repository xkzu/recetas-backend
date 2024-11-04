package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String nombreUsuario, String contrasena) {
        return usuarioRepository.login(nombreUsuario, contrasena);
    }
}
