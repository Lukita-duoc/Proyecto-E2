package cl.duoc.ordenes_service.feign;

import cl.duoc.ordenes_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="productos-service")
public interface ProductoFeign {
    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO buscarId(@PathVariable Long id);
}
