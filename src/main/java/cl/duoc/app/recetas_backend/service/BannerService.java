package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Banner;

import java.util.List;

public interface BannerService {

    Banner crearBanner(Banner banner);

    List<Banner> obtenerBanners();
}
