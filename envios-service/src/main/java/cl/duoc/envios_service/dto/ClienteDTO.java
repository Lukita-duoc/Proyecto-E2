package cl.duoc.envios_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClienteDTO {
    @JsonProperty("clienteId")
    private Long clienteId;
    private String nombreCompleto;
    private String correo;
}
