package ec.edu.espe.arias.examensegundoparcial.controller.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidarPagoRolResponse {

    private String mes;
    private String runEmpresa;
    private float valorTotal;
    private float valorReal;
    private int totalTransacciones;
    private int errores;
}
