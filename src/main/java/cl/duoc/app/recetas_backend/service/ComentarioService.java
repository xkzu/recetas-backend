package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Comentario;

import java.util.List;

public interface ComentarioService {

    Comentario save(Comentario comentario);

    List<Comentario> getAll();

    List<Comentario> getAllByIdReceta(Long id, boolean visible);

    void aprobarComentario(Long id, boolean visible);
}
