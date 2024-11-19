package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Comentario;
import cl.duoc.app.recetas_backend.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<Comentario> add(@RequestBody Comentario comentario) {
        try {
            return ResponseEntity.ok(comentarioService.save(comentario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(comentario);
        }
    }

    @GetMapping("all/{id}")
    public ResponseEntity<List<Comentario>> getAll(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(comentarioService.getAllByIdReceta(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
