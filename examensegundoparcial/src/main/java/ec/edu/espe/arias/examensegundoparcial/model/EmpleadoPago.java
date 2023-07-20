package ec.edu.espe.arias.examensegundoparcial.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmpleadoPago {

    private String numeroCuenta;
    private BigDecimal valor;

    @Builder.Default
    private String estado = "PEN";
}
