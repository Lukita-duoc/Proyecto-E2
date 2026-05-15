package cl.duoc.ordenes_service.dto;

import lombok.Data;

@Data
public class DetalleOrdenDTO {

    private Long idDetalle;
    private Integer cantidad;
    private Double subtotal;
    private ProductoDTO producto;

}
