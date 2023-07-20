package ec.edu.espe.arias.examensegundoparcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.arias.examensegundoparcial.model.Empleado;
import ec.edu.espe.arias.examensegundoparcial.model.Empresa;
import ec.edu.espe.arias.examensegundoparcial.repository.EmpresaRepository;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public void createEmpresa(Empresa empresa) {
        Optional<Empresa> optEmpresa = this.empresaRepository.findByRuc(empresa.getRuc());
        if (optEmpresa.isPresent()) {
            throw new RuntimeException("Ya existe una empresa con el ruc: " + empresa.getRuc());
        }
        this.empresaRepository.save(empresa);
    }

    public void addEmpleado(String ruc, Empleado empleado) {
        Optional<Empresa> optEmpresa = this.empresaRepository.findByEmpleadosCedula(empleado.getCedula());
        if(optEmpresa.isPresent()) {
            throw new RuntimeException("El señor " + empleado.getNombres() + " " + empleado.getApellidos() + " se encuentra trabajando. Cedula: " + empleado.getCedula());
        }
        Optional<Empresa> optEmpresa2 = this.empresaRepository.findByRuc(ruc);
        if(!optEmpresa2.isPresent()) {
            throw new RuntimeException("No existe la empresa cuyo ruc es: " + ruc);
        }
        Empresa empresa = optEmpresa2.get();
        empresa.getEmpleados().add(empleado);
        this.empresaRepository.save(empresa);
    }
}
