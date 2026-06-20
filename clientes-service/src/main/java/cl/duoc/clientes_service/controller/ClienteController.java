package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.exception.ErrorResponse;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Controlador para el CRUD de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Lista a todas las clientes",
            description = "Metodo que muestra una lista de todas las clientes registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clientes encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ClienteDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Clientes no encontradas",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Operation(
            summary = "Busca cliente",
            description = "Busca una cliente a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarid(@Valid @PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        if(c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @Operation(
            summary = "Busca Por ID una cliente",
            description = "Obtiene por ID y retorna un objeto ClaseDTO ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "ClienteDTO encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "ClienteDTO no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/buscardto/{id}")
    public ResponseEntity<?> listaDTO(@PathVariable Long id) {
        ClienteDTO listado = clienteService.buscarDTO(id);
        if(listado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(listado);
    }

    @Operation(
            summary = "Buscar Clientes de empresa",
            description = "Busca todas las clientes del mismo empresa"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clientes encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ClienteDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Clientes no encontradas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<?>> buscarPorEmpresa(@PathVariable Long empresaId) {
        List<Cliente> listaCliente = clienteService.findByIdempresa(empresaId);
        return ResponseEntity.ok(listaCliente);
    }

    @Operation(
            summary = "Crear Clientes",
            description = "Crea una Cliente y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Cliente creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Cliente no pudo ser creada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cliente){
        Cliente c = clienteService.save(cliente);
        if(c == null) return new ResponseEntity<>(c, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Clientes",
            description = "Busca las cliente por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Cliente eliminada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la cliente no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Clientes",
            description = "Busca las cliente por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente actualizada con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la cliente no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente c) {
        Cliente cliente = clienteService.update(c, id);
        if(cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @Operation(
            summary = "Busca cliente por rut",
            description = "Busca una cliente a travez del rut"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable int rut) {
        Cliente c = clienteService.findByRut(rut);
        if(c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }
}