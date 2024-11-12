package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Receta;

import java.util.List;
import java.util.Optional;

public interface RecetaService {

    List<Receta> findAll();

    Optional<Receta> findById(Long id);

    Optional<Receta> findByName(String name);

    List<Receta> findByCountry(String country);

    List<Receta> obtenerRecetasMasRecientes(int limite);

    List<Receta> obtenerRecetasMasPopulares(int limite);

    List<Receta> buscarRecetas(String nombre, String tipoCocina, String ingredientes, String paisOrigen, String dificultad);

    Receta register(Receta receta);
}
