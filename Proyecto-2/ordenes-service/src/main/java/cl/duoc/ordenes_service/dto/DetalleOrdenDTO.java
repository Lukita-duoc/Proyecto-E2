package cl.duoc.ordenes_service.dto;

import lombok.Data;

@Data
public class DetalleOrdenDTO {

    private Long productoId;
    private String nombre;
    private Integer cantidad;
}
