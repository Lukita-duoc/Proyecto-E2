package cl.duoc.ordenes_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdenResponseDTO {

    private Long idOrden;
    private LocalDateTime fecha;
    private String estado;
    private Integer total;
    private ClienteDTO cliente;
}
