package cl.duoc.inventario_service.feign;

import cl.duoc.inventario_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="empleados-service")

public interface SucursalFeign {
    @GetMapping("/api/v1/sucursales/listaDetallada")
    List<ProductoDTO> listarDTO();
}
