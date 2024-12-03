package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Comentario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByIdRecetaAndVisible(Long idReceta, boolean visible);

    @Modifying
    @Transactional
    @Query("UPDATE Comentario c SET c.visible = :visible WHERE c.id = :id")
    void actualizarVisibilidad(@Param("id") Long id, @Param("visible") boolean visible);
}
