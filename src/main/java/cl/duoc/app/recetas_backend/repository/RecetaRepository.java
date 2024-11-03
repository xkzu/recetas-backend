package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Receta;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    Optional<Receta> findByNombre(String nombre);

    List<Receta> findByPaisOrigen(String paisOrigen);

    List<Receta> findAllByOrderByFechaCreacionDesc(Pageable pageable);

    default List<Receta> findRecientes(int limite) {
        return findAllByOrderByFechaCreacionDesc(PageRequest.of(0, limite));
    }
}
