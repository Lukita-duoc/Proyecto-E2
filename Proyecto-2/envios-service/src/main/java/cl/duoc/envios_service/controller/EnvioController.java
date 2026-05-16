package cl.duoc.envios_service.controller;

import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.model.Envio;
import cl.duoc.envios_service.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Envio> e = envioService.findAll();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Envio e = envioService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<EnvioDTO> buscarPorIdDto(@PathVariable Long id) {
        EnvioDTO dto = envioService.buscarDTO(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<?> buscarPorIdOrden(@PathVariable Long ordenId) {
        Envio e = envioService.findByOrdenId(ordenId);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Envio envio) {
        Envio e = envioService.save(envio);
        if(e == null) return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @Valid @RequestBody Envio envio) {
        Envio actualizado = envioService.update(id, envio);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
