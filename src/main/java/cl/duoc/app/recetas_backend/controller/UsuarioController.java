package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.request.UsuarioReq;
import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.response.LoginResponse;
import cl.duoc.app.recetas_backend.service.UsuarioService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioReq user) {
        Optional<Usuario> usuario = usuarioService.login(user.getNombre(), user.getPassword());
        if (usuario.isPresent()) {
            String token = jwtUtil.generateToken(user.getNombre());
            return ResponseEntity.ok(new LoginResponse(usuario, token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}
