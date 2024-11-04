package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Receta;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Receta> findAllByOrderByPopularidadDesc(Pageable pageable);

    @Query("SELECT r FROM Receta r " +
            "WHERE (:nombre IS NULL OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:tipoCocina IS NULL OR LOWER(r.tipoCocina) LIKE LOWER(CONCAT('%', :tipoCocina, '%'))) " +
            "AND (:ingredientes IS NULL OR LOWER(r.ingredientes) LIKE LOWER(CONCAT('%', :ingredientes, '%'))) " +
            "AND (:paisOrigen IS NULL OR LOWER(r.paisOrigen) LIKE LOWER(CONCAT('%', :paisOrigen, '%'))) " +
            "AND (:dificultad IS NULL OR LOWER(r.dificultad) LIKE LOWER(CONCAT('%', :dificultad, '%')))")
    List<Receta> buscarRecetas(@Param("nombre") String nombre,
                               @Param("tipoCocina") String tipoCocina,
                               @Param("ingredientes") String ingredientes,
                               @Param("paisOrigen") String paisOrigen,
                               @Param("dificultad") String dificultad);
}
