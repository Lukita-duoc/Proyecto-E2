package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Sucursal> s = sucursalService.findAll();
        return ResponseEntity.ok(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Sucursal s = sucursalService.findById(id);
        if(s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listaDetallada() {
        List<SucursalDTO> dto = sucursalService.listaDetallada();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Sucursal sucursal) {
        Sucursal s = sucursalService.save(sucursal);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Sucursal s, @PathVariable Long id) {
        Sucursal sucursal = sucursalService.update(id, s);
        if(sucursal == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }


}
