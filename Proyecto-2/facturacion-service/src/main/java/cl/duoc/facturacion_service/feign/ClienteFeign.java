package cl.duoc.facturacion_service.feign;

import cl.duoc.facturacion_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-service")
public interface ClienteFeign {
    @GetMapping("/buscardto/{id}")
    ClienteDTO listaDTO(@PathVariable Long id);

    }

