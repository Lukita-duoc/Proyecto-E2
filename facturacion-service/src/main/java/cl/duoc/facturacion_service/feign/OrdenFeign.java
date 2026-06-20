package cl.duoc.facturacion_service.feign;

import cl.duoc.facturacion_service.dto.OrdenesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ordenes-service")
public interface OrdenFeign {

    @GetMapping("/api/v1/ordenes/detalle/{id}")
    OrdenesDTO buscarDTO(@PathVariable Long id);
}
