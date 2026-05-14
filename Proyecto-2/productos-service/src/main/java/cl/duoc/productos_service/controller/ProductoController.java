package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Producto> p = productoService.findAll();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Producto p = productoService.findById(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/listaDetallada/{id}")
    public ResponseEntity<?> listarDTO(@PathVariable Long id){
        ProductoDTO dto = productoService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Producto p) {
        Producto producto = productoService.save(p);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody Producto producto) {
        Producto p = productoService.update(id, producto);
        if(p == null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }
}
