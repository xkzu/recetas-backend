package cl.duoc.app.recetas_backend.response;

import cl.duoc.app.recetas_backend.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class LoginResponse {

    private Optional<Usuario> usuario;

    private final String token;

}