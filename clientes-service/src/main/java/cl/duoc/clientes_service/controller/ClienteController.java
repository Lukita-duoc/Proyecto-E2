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
            summary = "Lista a todos los clientes",
            description = "Metodo que muestra una lista de todos los clientes registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clientes encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ClienteDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Clientes no encontrados",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
            )
    )
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Operation(
            summary = "Busca cliente",
            description = "Busca un cliente a través de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente Encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarid(@Valid @PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        if(c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @Operation(
            summary = "Busca Por ID a un clienteDTO",
            description = "Obtiene por ID y retorna a un clienteDTO ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "ClienteDTO encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "ClienteDTO no encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
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
            description = "Busca todos los clientes de la misma empresa"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clientes encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ClienteDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Clientes no encontrados",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<?>> buscarPorEmpresa(@PathVariable Long empresaId) {
        List<Cliente> listaCliente = clienteService.findByIdempresa(empresaId);
        return ResponseEntity.ok(listaCliente);
    }

    @Operation(
            summary = "Crear Clientes",
            description = "Crea un Cliente y lo guarda en la BD"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente creado con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Cliente no pudo ser creado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
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
            description = "Busca los cliente por ID y los elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Cliente eliminado con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id del cliente no se encontró",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Clientes",
            description = "Busca los cliente por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente actualizado con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id del cliente no se encontró",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
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
            description = "Busca un cliente a través del rut"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cliente Encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor al procesar la solicitud.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "object", implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable int rut) {
        Cliente c = clienteService.findByRut(rut);
        if(c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }
}