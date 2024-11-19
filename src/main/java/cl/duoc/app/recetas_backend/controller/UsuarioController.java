package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.request.UsuarioReq;
import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.response.LoginResponse;
import cl.duoc.app.recetas_backend.service.UsuarioService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UsuarioReq user) {
        Optional<Usuario> usuario = usuarioService.findByNombreUsuario(user.getNombre());
        log.info("Usuario encontrado: {}", usuario);

        if (usuario.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), usuario.get().getContrasena())) {
                String token = jwtUtil.generateToken(user.getNombre());
                return ResponseEntity.ok(new LoginResponse(usuario, token));
            } else {
                if (usuario.get().getContrasena().equals(user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getNombre());
                    return ResponseEntity.ok(new LoginResponse(usuario, token));
                }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario user) {
        try {
            if (Util.isEmptyOrNull(user.getNombreUsuario())
                    || Util.isEmptyOrNull(user.getNombreCompleto())
                    || Util.isEmptyOrNull(user.getEmail())
                    || Util.isEmptyOrNull(user.getContrasena())) {
                return ResponseEntity.badRequest().build();
            }
            String encryptedPassword = passwordEncoder.encode(user.getContrasena());
            user.setContrasena(encryptedPassword);
            return ResponseEntity.ok(usuarioService.register(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
