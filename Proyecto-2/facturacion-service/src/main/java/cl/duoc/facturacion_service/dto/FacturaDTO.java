package cl.duoc.facturacion_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FacturaDTO {
    private Long idFactura;
    private LocalDate fechaEmision;
    private String metodoPago;
    private int total;
    private String nombreProducto;
    private Long idCliente;
    private Long idOrden;
    private List<String> nombresProductos;
}
