package ec.edu.espe.arias.examensegundoparcial.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.arias.examensegundoparcial.model.PagoRol;

@Repository
public interface PagoRolRepository extends MongoRepository<PagoRol, String> {

    Optional<PagoRol> findByMesAndRucEmpresa(String mes, String rucEmpresa);
}
