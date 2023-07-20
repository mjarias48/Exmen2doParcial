package ec.edu.espe.arias.examensegundoparcial.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ec.edu.espe.arias.examensegundoparcial.controller.dtos.AñadirEmpleadoRequest;
import ec.edu.espe.arias.examensegundoparcial.controller.dtos.CrearEmpresaRequest;
import ec.edu.espe.arias.examensegundoparcial.model.Empleado;
import ec.edu.espe.arias.examensegundoparcial.model.Empresa;

public class EmpresaMapper {

    public static Empresa map(CrearEmpresaRequest request) {
        return Empresa.builder()
                .ruc(request.getRuc())
                .razonSocial(request.getRazonSocial())
                .cuentaPrincipal(request.getCuentaPrincipal())
                .empleados(request.getEmpleados())
                .build();
    }

    public static Empleado map(AñadirEmpleadoRequest request) {
        return Empleado.builder()
                .cedula(request.getCedula())
                .apellidos(request.getApellidos())
                .nombres(request.getNombres())
                .numeroCuenta(request.getNumeroCuenta())
                .build();
    }

    public static List<Empleado> map(List<AñadirEmpleadoRequest> request) {
        return request.stream().map(req -> map(req)).collect(Collectors.toList());
    }
}
