package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Valoracion;
import cl.duoc.app.recetas_backend.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    private final ValoracionRepository valoracionRepository;

    @Autowired
    public ValoracionServiceImpl(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    @Override
    public Valoracion save(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    public List<Valoracion> findAll(Long id) {
        return valoracionRepository.findByIdReceta(id);
    }
}
