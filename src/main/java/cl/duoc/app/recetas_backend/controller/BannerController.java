package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Banner;
import cl.duoc.app.recetas_backend.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banners")
public class BannerController {


    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping
    public ResponseEntity<Banner> crearBanner(@RequestBody Banner banner) {
        Banner nuevoBanner = bannerService.crearBanner(banner);
        return ResponseEntity.ok(nuevoBanner);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Banner>> obtenerBanners() {
        List<Banner> banners = bannerService.obtenerBanners();
        return ResponseEntity.ok(banners);
    }
}
