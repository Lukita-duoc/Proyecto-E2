package cl.duoc.envios_service.feign;

import cl.duoc.envios_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-service")
public interface ClienteFeign {
    @GetMapping("/api/v1/clientes/buscardto/{id}")
    ClienteDTO buscarDTO (@PathVariable Long id);

}
