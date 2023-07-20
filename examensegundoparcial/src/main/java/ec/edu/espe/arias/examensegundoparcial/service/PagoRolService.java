package ec.edu.espe.arias.examensegundoparcial.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ec.edu.espe.arias.examensegundoparcial.controller.dtos.ValidarPagoRolResponse;
import ec.edu.espe.arias.examensegundoparcial.model.EmpleadoPago;
import ec.edu.espe.arias.examensegundoparcial.model.Empresa;
import ec.edu.espe.arias.examensegundoparcial.model.PagoRol;
import ec.edu.espe.arias.examensegundoparcial.repository.EmpresaRepository;
import ec.edu.espe.arias.examensegundoparcial.repository.PagoRolRepository;

@Service
public class PagoRolService {

    private final PagoRolRepository pagoRolRepository;
    private final EmpresaRepository empresaRepository;

    public PagoRolService(PagoRolRepository pagoRolRepository, EmpresaRepository empresaRepository) {
        this.pagoRolRepository = pagoRolRepository;
        this.empresaRepository = empresaRepository;
    }

    public void createPagoRol(PagoRol pago) {
        Optional<Empresa> optEmpresa = this.empresaRepository.findByRuc(pago.getRucEmpresa());
        if (!optEmpresa.isPresent()) {
            throw new RuntimeException("No existe la empresa de ruc: " + pago.getRucEmpresa());
        }
        List<EmpleadoPago> empleadosPago = pago.getEmpleadosPagos();
        double valorTotal = 0;
        for (EmpleadoPago empleadoPago : empleadosPago) {
            valorTotal += empleadoPago.getValor().doubleValue();
        }
        pago.setValorTotal(new BigDecimal(valorTotal));
        pago.setValorReal(new BigDecimal(0));

        this.pagoRolRepository.save(pago);
    }

    public ValidarPagoRolResponse validatePagoRol(String mes, String ruc) {
        Optional<Empresa> optEmpresa = this.empresaRepository.findByRuc(ruc);
        if (!optEmpresa.isPresent()) {
            throw new RuntimeException("No existe la empresa de ruc: " + ruc);
        }
        Optional<PagoRol> optPagoRol = this.pagoRolRepository.findByMesAndRucEmpresa(mes, ruc);
        if (!optPagoRol.isPresent()) {
            throw new RuntimeException("No existe el rol de pago del mes: " + mes + " y ruc: " + ruc);
        }
        Empresa empresa = optEmpresa.get();
        PagoRol pagoRol = optPagoRol.get();

        List<String> empleadosNumeroCuenta = empresa.getEmpleados().stream().map(emp -> emp.getNumeroCuenta())
                .collect(Collectors.toList());
        List<String> empleadosPagoNumeroCuenta = pagoRol.getEmpleadosPagos().stream().map(emp -> emp.getNumeroCuenta())
                .collect(Collectors.toList());

        float valorReal = 0;
        int totalOkayTransactions = 0;
        int totalErrorTransactions = 0;
        for (int i = 0; i < empleadosPagoNumeroCuenta.size(); i++) {
            String currentNumeroCuenta = empleadosPagoNumeroCuenta.get(i);
            int auxIndex = empleadosNumeroCuenta.indexOf(currentNumeroCuenta);
            if (auxIndex == -1) {
                pagoRol.getEmpleadosPagos().get(i).setEstado("VAL");
                valorReal += pagoRol.getEmpleadosPagos().get(i).getValor().doubleValue();
                totalOkayTransactions++;
            } else {
                pagoRol.getEmpleadosPagos().get(i).setEstado("BAD");
                totalErrorTransactions++;
            }
        }

        pagoRol.setValorReal(new BigDecimal(valorReal));
        this.pagoRolRepository.save(pagoRol);

        return ValidarPagoRolResponse.builder()
                .mes(mes)
                .runEmpresa(ruc)
                .valorTotal(pagoRol.getValorTotal().floatValue())
                .valorReal(valorReal)
                .totalTransacciones(totalOkayTransactions)
                .errores(totalErrorTransactions)
                .build();
    }
}
