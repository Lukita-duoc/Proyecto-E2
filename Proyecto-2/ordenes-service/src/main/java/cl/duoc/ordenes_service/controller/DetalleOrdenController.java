package cl.duoc.ordenes_service.controller;

import cl.duoc.ordenes_service.model.DetalleOrden;
import cl.duoc.ordenes_service.service.DetalleOrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @GetMapping
    public ResponseEntity<List<DetalleOrden>> listar() {
        List<DetalleOrden> d = detalleOrdenService.findAll();
        return ResponseEntity.ok(d);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> buscarPorId(@PathVariable Long id) {
        DetalleOrden d = detalleOrdenService.findById(id);
        if(d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<DetalleOrden> guardar(@Valid @RequestBody DetalleOrden detalle) {
        DetalleOrden d = detalleOrdenService.guardarDetalle(detalle);
        if(d == null) return new ResponseEntity<>(d, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleOrdenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrden> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleOrden detalle) {
        DetalleOrden d = detalleOrdenService.update(id, detalle);
        if(d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }
}
