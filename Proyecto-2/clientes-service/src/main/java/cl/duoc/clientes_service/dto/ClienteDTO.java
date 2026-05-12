package cl.duoc.clientes_service.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombreCompleto;
    private String correo;
    private String nombreEmpresa;
}
