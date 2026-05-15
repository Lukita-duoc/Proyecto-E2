package cl.duoc.facturacion_service.dto;

import lombok.Data;

@Data
public class DetalleOrdenDTO {
    private Long productoId;
    private String nombre;
    private int cantidad;
}
