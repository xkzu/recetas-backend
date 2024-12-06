package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Valoracion;
import cl.duoc.app.recetas_backend.service.ValoracionService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/valoracion")
public class ValoracionController {

    private final ValoracionService valoracionService;

    private final JwtUtil jwtUtil;

    @Autowired
    public ValoracionController(ValoracionService valoracionService, JwtUtil jwtUtil) {
        this.valoracionService = valoracionService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/add")
    public ResponseEntity<Valoracion> addValoracion(@RequestBody Valoracion valoracion, @RequestHeader("Authorization") String token) {
        try {
            if (!Util.validateToken(token, jwtUtil)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(valoracionService.save(valoracion));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(valoracion);
        }
    }

    @GetMapping("all/{id}")
    public ResponseEntity<List<Valoracion>> findAll(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            if (!Util.validateToken(token, jwtUtil)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(valoracionService.findAll(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
