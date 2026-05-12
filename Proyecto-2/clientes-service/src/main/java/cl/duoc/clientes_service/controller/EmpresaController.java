package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        EmpresaDTO e = empresaService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Empresa e) {
        Empresa empresa = empresaService.save(e);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Empresa e) {
        Empresa empresa = empresaService.update(e, id);
        if(empresa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empresa);
    }

}
