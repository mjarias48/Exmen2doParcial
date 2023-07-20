package ec.edu.espe.arias.examensegundoparcial.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "empresa")
public class Empresa {

    @Id
    private String id;

    @Indexed(unique = true)
    private String ruc;
    
    private String razonSocial;
    private String cuentaPrincipal;
    private List<Empleado> empleados;

    @Version
    private Long version;
}
