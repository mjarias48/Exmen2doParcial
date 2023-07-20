package ec.edu.espe.arias.examensegundoparcial.controller.dtos;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.espe.arias.examensegundoparcial.model.EmpleadoPago;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CrearPagoRolRequest {

    private String mes;
    private LocalDateTime fechaProceso;
    private String rucEmpresa;
    private String cuentaPrincipal;
    private List<EmpleadoPago> empleadosPagos;

}
