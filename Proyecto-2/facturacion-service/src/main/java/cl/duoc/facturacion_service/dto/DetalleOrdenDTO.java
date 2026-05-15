package cl.duoc.facturacion_service.dto;

import lombok.Data;

@Data
public class DetalleOrdenDTO {
    private Long id;
    private int cantidad;
    private Long productoId;
}
