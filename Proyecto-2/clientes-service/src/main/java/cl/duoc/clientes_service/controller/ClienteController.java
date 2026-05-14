package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarid(@Valid @PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        if(c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<List<?>> listaDTO() {
        List<ClienteDTO> listado = clienteService.listaDetallada();
        return ResponseEntity.ok(listado);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cliente){
        Cliente c = clienteService.save(cliente);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente c) {
        Cliente cliente = clienteService.update(c, id);
        if(cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }


}
