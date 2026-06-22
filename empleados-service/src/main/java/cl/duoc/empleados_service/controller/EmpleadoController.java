package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.exception.ErrorResponse;
import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.service.EmpleadoService;
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
@RequestMapping("/api/v1/empleados")
@Tag(name = "Empleados",
     description = "Controlador para el CRUD de empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Operation(
            summary = "Lista a todos los empleados",
            description = "Metodo que muestra una lista de todos los empleados registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empleados encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = EmpleadoDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Empleados no encontrados",
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
        List<Empleado> e = empleadoService.findAll();
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Busca empleados",
            description = "Busca a un empleado a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empleado Encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpleadoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Empleado no encontrado",
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
        Empleado e = empleadoService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Listado de Empleados DTO",
            description = "Retorna una lista de empleadosDTO."
    )
    @ApiResponse(
            responseCode = "200",
            description = "EmpleadosDTO encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpleadoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "EmpleadosDTO no encontrados",
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
    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listaDetallada() {
        List<EmpleadoDTO> dto = empleadoService.listaDetallada();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Busca Empleados de una sucursal",
            description = "Busca todos las empleados de una misma sucursal"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empleados encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = EmpleadoDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Empleados no encontrados",
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
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<?>> buscarPorIdSucursal(@PathVariable Long idSucursal) {
        List<Empleado> empleados = empleadoService.findByIdSucursal(idSucursal);
        return ResponseEntity.ok(empleados);
    }

    @Operation(
            summary = "Crea Empleados",
            description = "Crea un Empleado y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empleado creado con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpleadoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Empleado no pudo ser creado",
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Empleado empleado) {
        Empleado e = empleadoService.save(empleado);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Empleados",
            description = "Busca los empleado por ID y los elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Empleado eliminado con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id del empleado no se encontró",
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
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Empleados",
            description = "Busca los empleado por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Empleado actualizado con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpleadoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id del empleado no se encontró",
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
    public ResponseEntity<?> actualizar(@Valid @RequestBody Empleado e, @PathVariable Long id) {
        Empleado empleado = empleadoService.update(id, e);
        if(empleado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empleado);
    }
}
