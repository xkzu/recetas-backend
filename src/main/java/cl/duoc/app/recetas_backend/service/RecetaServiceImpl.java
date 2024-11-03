package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Receta;
import cl.duoc.app.recetas_backend.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository repo;

    @Autowired
    public RecetaServiceImpl(RecetaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Receta> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Receta> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Receta> findByName(String name) {
        return repo.findByNombre(name);
    }

    @Override
    public List<Receta> findByCountry(String pais) {
        return repo.findByPaisOrigen(pais);
    }

    public List<Receta> obtenerRecetasMasRecientes(int limite) {
        return repo.findRecientes(limite);
    }
}
