package cl.duoc.envios_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrdenDTO {
    private Long idOrden;
    private String estado;
    private int total;
    private String nombreCompleto;
    private String correo;
}
