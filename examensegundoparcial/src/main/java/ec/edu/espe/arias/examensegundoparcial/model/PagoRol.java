package ec.edu.espe.arias.examensegundoparcial.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pago_rol")
public class PagoRol {

    @Id
    private String id;

    private String mes;
    private LocalDateTime fechaProceso;
    private String rucEmpresa;
    private String cuentaPrincipal;
    private BigDecimal valorTotal;

    @Builder.Default
    private BigDecimal valorReal = new BigDecimal(0);
    private List<EmpleadoPago> empleadosPagos;

    @Version
    private Long version;
}
