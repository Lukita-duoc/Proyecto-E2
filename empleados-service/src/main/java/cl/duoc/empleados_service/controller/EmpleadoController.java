package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Empleado> e = empleadoService.findAll();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Empleado e = empleadoService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listaDetallada() {
        List<EmpleadoDTO> dto = empleadoService.listaDetallada();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<?>> buscarPorIdSucursal(@PathVariable Long idSucursal) {
        List<Empleado> empleados = empleadoService.findByIdSucursal(idSucursal);
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Empleado empleado) {
        Empleado e = empleadoService.save(empleado);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Empleado e, @PathVariable Long id) {
        Empleado empleado = empleadoService.update(id, e);
        if(empleado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empleado);
    }
}
