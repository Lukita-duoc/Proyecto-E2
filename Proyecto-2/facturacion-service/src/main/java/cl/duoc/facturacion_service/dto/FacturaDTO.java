package cl.duoc.facturacion_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
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
