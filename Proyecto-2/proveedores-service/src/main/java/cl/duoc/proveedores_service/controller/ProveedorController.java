package cl.duoc.proveedores_service.controller;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<?>> listar(){
        List<Proveedor> p = proveedorService.findAll();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        Proveedor p = proveedorService.findById(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<ProveedorDTO> dto = proveedorService.listaDetallada();
        if(dto == null)return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> guardar (@Valid @RequestBody Proveedor p){
        Proveedor proveedor = proveedorService.save(p);
        return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor){
        Proveedor p = proveedorService.update(proveedor, id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }
}