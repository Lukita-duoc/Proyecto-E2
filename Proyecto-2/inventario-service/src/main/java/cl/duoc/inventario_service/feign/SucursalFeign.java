package cl.duoc.inventario_service.feign;

import cl.duoc.inventario_service.dto.ProductoDTO;
import cl.duoc.inventario_service.dto.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="empleados-service")

public interface SucursalFeign {
    @GetMapping("/api/v1/sucursales/listaDetallada/{id}")
    SucursalDTO buscarDTO(@PathVariable Long id);
}
