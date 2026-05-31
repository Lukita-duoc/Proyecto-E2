package cl.duoc.ordenes_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenDTO {

    private Long idOrden;
    private LocalDateTime fecha;
    private String estado;
    private int total;
    private String nombreCompleto;
    private String correo;
    private List<DetalleOrdenDTO> detalle;
}
