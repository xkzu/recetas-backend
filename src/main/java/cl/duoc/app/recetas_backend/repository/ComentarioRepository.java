package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {


    List<Comentario> findByIdReceta(Long idReceta);
}
