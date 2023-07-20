package ec.edu.espe.arias.examensegundoparcial.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.arias.examensegundoparcial.controller.dtos.AñadirEmpleadoRequest;
import ec.edu.espe.arias.examensegundoparcial.controller.dtos.CrearEmpresaRequest;
import ec.edu.espe.arias.examensegundoparcial.controller.mapper.EmpresaMapper;
import ec.edu.espe.arias.examensegundoparcial.model.Empleado;
import ec.edu.espe.arias.examensegundoparcial.model.Empresa;
import ec.edu.espe.arias.examensegundoparcial.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity createEmpresa(
            @RequestBody CrearEmpresaRequest request) {
        try {
            Empresa empresa = EmpresaMapper.map(request);
            this.empresaService.createEmpresa(empresa);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{ruc}")
    public ResponseEntity añadirEmpleado(
            @PathVariable("ruc") String ruc,
            @RequestBody AñadirEmpleadoRequest request) {
        try {
            Empleado empleado = EmpresaMapper.map(request);
            this.empresaService.addEmpleado(ruc, empleado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
