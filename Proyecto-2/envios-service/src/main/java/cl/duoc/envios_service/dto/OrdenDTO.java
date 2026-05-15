package cl.duoc.envios_service.dto;

import lombok.Data;

@Data
public class OrdenDTO {
    private Long idOrden;
    private Long clienteId;
    private String estado;
    private int total;
}
