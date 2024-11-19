package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Comentario;

import java.util.List;

public interface ComentarioService {

    Comentario save(Comentario comentario);

    List<Comentario> getAllByIdReceta(Long id);
}
