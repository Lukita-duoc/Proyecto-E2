package cl.duoc.facturacion_service.controller;

import cl.duoc.facturacion_service.dto.FacturaDTO;
import cl.duoc.facturacion_service.exception.ErrorResponse;
import cl.duoc.facturacion_service.model.Factura;
import cl.duoc.facturacion_service.service.FacturaService;
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
@RequestMapping("/api/v1/facturas")
@Tag(name = "Facturacion", description = "Controlador para el CRUD de facturacion")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Operation(
            summary = "Lista a todas las facturas",
            description = "Metodo que muestra una lista de todas las facturas registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Facturas encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = FacturaDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Facturas no encontradas",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Factura> f = facturaService.findAll();
        return ResponseEntity.ok(f);
    }

    @Operation(
            summary = "Busca factura",
            description = "Busca una factura a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Factura Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FacturaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Factura no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)

            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Factura f = facturaService.findById(id);
        if(f == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(f);
    }

    @Operation(
            summary = "Busca Por ID una factura",
            description = "Obtiene por ID y retorna un objeto ClaseDTO ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "FacturaDTO encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FacturaDTO.class)

            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "FacturaDTO no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)

            )
    )
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> buscarIdDto(@PathVariable Long id) {
        FacturaDTO dto = facturaService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Buscar Facturas de cliente",
            description = "Busca todas las facturas del mismo cliente"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Facturas encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = FacturaDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Facturas no encontradas",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)

            )
    )
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<?>> listarPorIdCliente(@PathVariable Long idCliente) {
        List<Factura> facturas = facturaService.findByIdCliente(idCliente);
        return ResponseEntity.ok(facturas);
    }

    @Operation(
            summary = "Crear Facturas",
            description = "Crea una Factura y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Factura creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FacturaDTO.class)

            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Factura no pudo ser creada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Factura factura) {
        Factura f = facturaService.save(factura);
        if(f == null) return new ResponseEntity<>(f, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }


    @Operation(
            summary = "eliminar Facturas",
            description = "Busca las factura por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Factura eliminada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la factura no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
