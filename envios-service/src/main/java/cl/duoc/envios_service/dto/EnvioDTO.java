package cl.duoc.envios_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnvioDTO {
    private Long idEnvio;
    private String transportista;
    private String destino;
    private String estadoEnvio;
    private LocalDate fechaSalida;
    private Long ordenId;
    private String nombreCliente;
    private String correoCliente;
    private int total;

}
