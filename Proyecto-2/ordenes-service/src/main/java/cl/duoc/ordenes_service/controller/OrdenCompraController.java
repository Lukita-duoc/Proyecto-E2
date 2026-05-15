package cl.duoc.ordenes_service.controller;

import cl.duoc.ordenes_service.dto.OrdenDTO;
import cl.duoc.ordenes_service.model.OrdenCompra;
import cl.duoc.ordenes_service.service.OrdenCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping
    public ResponseEntity<List<OrdenCompra>> findAll() {
        List<OrdenCompra> o = ordenCompraService.findAll();
        return ResponseEntity.ok(o);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> buscarPorId(@PathVariable Long id) {
        OrdenCompra o = ordenCompraService.findById(id);
        if(o == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(o);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<OrdenDTO> buscarPorIdDTO(@PathVariable Long id) {
        OrdenDTO dto = ordenCompraService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrdenCompra> guardar(@Valid @RequestBody OrdenCompra orden) {
        OrdenCompra o = ordenCompraService.save(orden);
        if(o == null) return new ResponseEntity<>(o, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenCompraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompra> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenCompra orden) {
        OrdenCompra o = ordenCompraService.update(orden, id);
        if(o == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(o);
    }
}
