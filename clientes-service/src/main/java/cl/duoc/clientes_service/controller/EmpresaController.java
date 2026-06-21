package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.exception.ErrorResponse;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.service.EmpresaService;
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
@RequestMapping("/api/v1/empresas")

@Tag(name = "Empresas", description = "Controlador para el CRUD de empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(
            summary = "Lista a todas las empresas",
            description = "Metodo que muestra una lista de todas las empresas registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empresas encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = EmpresaDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Empresas no encontradas",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<?>> listar() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @Operation(
            summary = "Busca empresa",
            description = "Busca una empresa a través de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empresa Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Empresa no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Empresa e = empresaService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Busca Por ID una empresa",
            description = "Obtiene por ID y retorna un objeto ClaseDTO."
    )
    @ApiResponse(
            responseCode = "200",
            description = "EmpresaDTO encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "EmpresaDTO no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO () {
        List<EmpresaDTO> listaDTO = empresaService.listaDetallada();
        return ResponseEntity.ok(listaDTO);
    }

    @Operation(
            summary = "Crear Empresas",
            description = "Crea una Empresa y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Empresa creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Empresa no pudo ser creada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Empresa e) {
        Empresa empresa = empresaService.save(e);
        if(empresa == null) return new ResponseEntity<>(empresa, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Empresas",
            description = "Busca las empresa por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Empresa actualizada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la empresa no se encontró.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Empresas",
            description = "Busca las empresa por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empresa actualizada con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la empresa no se encontró.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Empresa e) {
        Empresa empresa = empresaService.update(e, id);
        if(empresa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empresa);
    }
}