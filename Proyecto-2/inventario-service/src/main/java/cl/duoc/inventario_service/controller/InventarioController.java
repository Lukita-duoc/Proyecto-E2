package cl.duoc.inventario_service.controller;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        List<Inventario> i = inventarioService.findAll();
        return ResponseEntity.ok(i);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        Inventario i = inventarioService.findById(id);
        if(i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(i);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<InventarioDTO> buscarPorIdDto(@PathVariable Long id) {
        InventarioDTO dto = inventarioService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@Valid @RequestBody Inventario inventario) {
        Inventario i = inventarioService.save(inventario);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @Valid @RequestBody Inventario i) {
        Inventario inventario = inventarioService.update(i, id);
        if(inventario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventario);
    }




}
