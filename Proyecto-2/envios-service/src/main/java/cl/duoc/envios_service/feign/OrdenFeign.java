package cl.duoc.envios_service.feign;

import cl.duoc.envios_service.dto.OrdenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ordenes-service")
public interface OrdenFeign {

    @GetMapping("/api/v1/ordenes/detalle/{id}")
    OrdenDTO buscarPorIdDTO(@PathVariable Long id);
}
