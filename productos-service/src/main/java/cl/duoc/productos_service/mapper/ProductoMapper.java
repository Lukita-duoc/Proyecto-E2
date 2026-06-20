package cl.duoc.productos_service.mapper;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto p) {
        if(p == null) return null;
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setCategoria(p.getCategoria());

        return dto;
    }
}
