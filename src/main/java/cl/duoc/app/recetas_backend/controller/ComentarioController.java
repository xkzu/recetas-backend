package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Comentario;
import cl.duoc.app.recetas_backend.service.ComentarioService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    private static final Logger log = LoggerFactory.getLogger(ComentarioController.class);
    private final ComentarioService comentarioService;

    private final JwtUtil jwtUtil;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, JwtUtil jwtUtil) {
        this.comentarioService = comentarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/add")
    public ResponseEntity<Comentario> add(@RequestBody Comentario comentario, @RequestHeader("Authorization") String token) {
        try {
            if (!Util.validateToken(token, jwtUtil)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(comentarioService.save(comentario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(comentario);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Comentario>> getAll(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            if (!Util.validateToken(token, jwtUtil)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(comentarioService.getAllByIdReceta(id, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comentario>> getAllAdmin(@RequestHeader("Authorization") String token) {
        try {
            log.info("token {}", token);
            if (!Util.validateToken(token, jwtUtil)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(comentarioService.getAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/aprobar/{id}")
    public ResponseEntity<Comentario> aprobar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            if (!Util.validateToken(token, jwtUtil)) {
                throw new SecurityException("Token inválido");
            }
            comentarioService.aprobarComentario(id, true);
            return ResponseEntity.ok().build();

        } catch (SecurityException se) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
