package cl.duoc.facturacion_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdenesDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Long clienteId;

    private List<DetalleOrdenDTO> detalles;
}
