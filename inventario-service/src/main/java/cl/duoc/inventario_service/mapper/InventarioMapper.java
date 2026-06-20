package cl.duoc.inventario_service.mapper;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioDTO toDTO(Inventario i) {
        InventarioDTO dto = new InventarioDTO();

        dto.setId(i.getIdInventario());
        dto.setCantidad(i.getStockActual());
        dto.setProductoId(i.getProductoId());
        dto.setSucursalId(i.getSucursalId());

        return dto;
    }

}
