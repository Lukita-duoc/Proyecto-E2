package cl.duoc.ordenes_service.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long idCliente;
    private String nombreCompleto;
    private String correo;
    private String nombreEmpresa;

}

