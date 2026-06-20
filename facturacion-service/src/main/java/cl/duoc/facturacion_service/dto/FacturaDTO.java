package cl.duoc.facturacion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    //private Long idFactura;
    private LocalDateTime fechaEmision;
    private String metodoPago;
    private int total;
    //private Long idCliente;
    //private Long idOrden;
    private String nombreCompleto;
    private String correo;
    private List<String> nombresProductos;
}
