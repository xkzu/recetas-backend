package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Banner;
import cl.duoc.app.recetas_backend.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public Banner crearBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    public List<Banner> obtenerBanners() {
        return bannerRepository.findAll();
    }
}
