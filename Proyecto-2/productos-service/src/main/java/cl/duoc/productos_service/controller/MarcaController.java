package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Marca> m = marcaService.findAll();
        return ResponseEntity.ok(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Marca m = marcaService.findById(id);
        if(m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<MarcaDTO> dto = marcaService.listaDetallada();
        if(dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Marca m) {
        Marca marca = marcaService.save(m);
        return new ResponseEntity<>(marca, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody Marca marca) {
        Marca m = marcaService.update(id, marca);
        if(m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }
}
