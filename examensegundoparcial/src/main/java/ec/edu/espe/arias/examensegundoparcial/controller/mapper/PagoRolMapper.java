package ec.edu.espe.arias.examensegundoparcial.controller.mapper;

import ec.edu.espe.arias.examensegundoparcial.controller.dtos.CrearPagoRolRequest;
import ec.edu.espe.arias.examensegundoparcial.model.PagoRol;

public class PagoRolMapper {
    public static PagoRol map(CrearPagoRolRequest request) {
        return PagoRol.builder()
                .mes(request.getMes())
                .fechaProceso(request.getFechaProceso())
                .rucEmpresa(request.getRucEmpresa())
                .cuentaPrincipal(request.getCuentaPrincipal())
                .empleadosPagos(request.getEmpleadosPagos())
                .build();
    }
}
