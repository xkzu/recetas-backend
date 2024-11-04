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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo_cocina")
    private String tipoCocina;

    @Column(name = "ingredientes", length = 2000)
    private String ingredientes;

    @Column(name = "pais_origen")
    private String paisOrigen;

    @Column(name = "dificultad")
    private String dificultad;

    @Column(name = "popularidad")
    private int popularidad;

    @Column(name = "instrucciones_preparacion", length = 5000)
    private String instruccionesPreparacion;

    @Column(name = "tiempo_coccion")
    private int tiempoCoccion;

    @Column(name = "porciones")
    private int porciones;

    @Column(name = "fotografia_url")
    private String fotografiaUrl;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
}
