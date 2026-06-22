package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.exception.ErrorResponse;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.service.SucursalService;
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
@RequestMapping("/api/v1/sucursales")

@Tag(name = "Sucursales", description = "Controlador para el CRUD de las sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(
            summary = "Lista a todas las sucursales",
            description = "Metodo que muestra una lista de todas las sucursales registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Sucursales encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = SucursalDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Sucursales no encontradas",
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
    public ResponseEntity<List<?>> listar() {
        List<Sucursal> s = sucursalService.findAll();
        return ResponseEntity.ok(s);
    }

    //BUSCAR ID
    @Operation(
            summary = "Busca sucursales",
            description = "Busca una sucursal a través de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Sucursal Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SucursalDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Sucursal no encontrada",
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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Sucursal s = sucursalService.findById(id);
        if(s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    
    //LISTA DETALLADA
    @Operation(
            summary = "Busca sucursal",
            description = "Busca una sucursal a través de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Sucursal Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SucursalDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Sucursal no encontrada",
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
    @GetMapping("/listaDetallada/{id}")
    public ResponseEntity<?> listaDetallada(@PathVariable Long id) {
        SucursalDTO dto = sucursalService.buscarDTO(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Crear Sucursal",
            description = "Crea una Sucursal y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Sucursal creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SucursalDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Sucursal no pudo ser creada",
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Sucursal sucursal) {
        Sucursal s = sucursalService.save(sucursal);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar sucursales",
            description = "Busca las sucursales por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Sucursal eliminada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de sucursal no se encontró",
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
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Sucursales",
            description = "Busca las sucursales por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Sucursal actualizada con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SucursalDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la sucursal no se encontró",
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
    public ResponseEntity<?> actualizar(@Valid @RequestBody Sucursal s, @PathVariable Long id) {
        Sucursal sucursal = sucursalService.update(id, s);
        if(sucursal == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }


}
