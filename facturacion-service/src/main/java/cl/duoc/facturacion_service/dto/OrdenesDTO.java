package cl.duoc.facturacion_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenesDTO {
    private int total;
    private List<DetalleOrdenDTO> detalle;
}
