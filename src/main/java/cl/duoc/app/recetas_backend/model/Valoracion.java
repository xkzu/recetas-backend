package cl.duoc.app.recetas_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "valoraciones")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receta_id", nullable = false)
    private Long idReceta;

    @Column(name = "usuario_id", nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Integer puntaje;
}
