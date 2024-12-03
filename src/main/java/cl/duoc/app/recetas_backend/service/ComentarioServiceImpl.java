package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Comentario;
import cl.duoc.app.recetas_backend.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> getAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public List<Comentario> getAllByIdReceta(Long id, boolean visible) {
        return comentarioRepository.findByIdRecetaAndVisible(id, visible);
    }

    @Override
    public void aprobarComentario(Long id, boolean visible) {
        comentarioRepository.actualizarVisibilidad(id, visible);
    }
}
