package cl.duoc.ordenes_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdenDTO {

    private Long clienteId;
    private String estado;
}
