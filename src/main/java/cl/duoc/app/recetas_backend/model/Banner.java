package cl.duoc.app.recetas_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empresa_nombre", nullable = false)
    private String empresaNombre;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "url_imagen")
    private String urlImagen;

    @Column(name = "link_website")
    private String linkWebsite;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion = new Date();
}
