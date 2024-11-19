package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByIdReceta(Long idReceta);
}
