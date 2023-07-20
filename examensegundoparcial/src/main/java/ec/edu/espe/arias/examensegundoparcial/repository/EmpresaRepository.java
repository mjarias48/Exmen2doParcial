package ec.edu.espe.arias.examensegundoparcial.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.arias.examensegundoparcial.model.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    Optional<Empresa> findByRuc(String ruc);

    Optional<Empresa> findByEmpleadosCedula(String cedula);
}
