package ec.edu.espe.arias.examensegundoparcial.model;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Empleado {

    @Indexed(unique = true)
    private String cedula;
    private String apellidos;
    private String nombres;
    private String numeroCuenta;
}
