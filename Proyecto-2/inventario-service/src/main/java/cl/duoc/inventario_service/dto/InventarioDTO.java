package cl.duoc.inventario_service.dto;

import lombok.Data;

@Data
public class InventarioDTO {
    private Long id;
    private int cantidad;
    private Long productoId;
    private Long sucursalId;

}
