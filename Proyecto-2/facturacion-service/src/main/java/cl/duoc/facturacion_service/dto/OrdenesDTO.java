package cl.duoc.facturacion_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenesDTO {
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private Integer total;
    private Long clienteId;

    private List<DetalleOrdenDTO> detalles;
}
