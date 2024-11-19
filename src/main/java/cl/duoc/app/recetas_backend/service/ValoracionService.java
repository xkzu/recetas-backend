package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Valoracion;

import java.util.List;

public interface ValoracionService {

    Valoracion save(Valoracion valoracion);

    List<Valoracion> findAll(Long id);
}
