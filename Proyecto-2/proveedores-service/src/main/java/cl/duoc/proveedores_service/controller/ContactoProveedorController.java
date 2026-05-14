package cl.duoc.proveedores_service.controller;

import cl.duoc.proveedores_service.dto.ContactoProveedorDTO;
import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.model.ContactoProveedor;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.service.ContactoProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contactoproveedores")
public class ContactoProveedorController {

    @Autowired
    private ContactoProveedorService conProService;

    @GetMapping
    public ResponseEntity<List<?>> listar(){
        List<ContactoProveedor> cop = conProService.findAll();
        return ResponseEntity.ok(cop);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        ContactoProveedor cop = conProService.findById(id);
        if(cop == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cop);
    }

    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<ContactoProveedorDTO> dto = conProService.listaDetallada();
        if(dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ContactoProveedor cop){
        ContactoProveedor contactoProveedor = conProService.save(cop);
        return new ResponseEntity<>(contactoProveedor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        conProService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody ContactoProveedor contactoProveedor) {
        ContactoProveedor cop = conProService.update(contactoProveedor, id);
        if (cop == null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(cop);
    }
}
