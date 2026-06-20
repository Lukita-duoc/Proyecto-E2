package cl.duoc.facturacion_service.controller;

import cl.duoc.facturacion_service.dto.FacturaDTO;
import cl.duoc.facturacion_service.model.Factura;
import cl.duoc.facturacion_service.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;


    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Factura> f = facturaService.findAll();
        return ResponseEntity.ok(f);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Factura f = facturaService.findById(id);
        if(f == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(f);
    }


    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> buscarIdDto(@PathVariable Long id) {
        FacturaDTO dto = facturaService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<?>> listarPorIdCliente(@PathVariable Long idCliente) {
        List<Factura> facturas = facturaService.findByIdCliente(idCliente);
        return ResponseEntity.ok(facturas);
    }


    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Factura factura) {
        Factura f = facturaService.save(factura);
        if(f == null) return new ResponseEntity<>(f, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
