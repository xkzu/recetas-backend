package cl.duoc.app.recetas_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioReq {

    private String nombre;

    private String password;
}
