package cl.duoc.inventario_service.feign;

import cl.duoc.inventario_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="productos-service")
public interface ProductoFeign {
    @GetMapping("/api/v1/productos/listaDetallada")
    List<ProductoDTO> listarDTO();
}
