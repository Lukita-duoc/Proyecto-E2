package cl.duoc.proveedores_service.controller;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.exception.ErrorResponse;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Operation(
            summary = "Lista Proveedores",
            description = "Lista a todos los proveedores registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Proveedores encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProveedorDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Proveedores no encontrados",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<?>> listar(){
        List<Proveedor> p = proveedorService.findAll();
        return ResponseEntity.ok(p);
    }

    @Operation(
            summary = "Busca a un Proveedor",
            description = "Busca un Proveedor a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Proveedor Encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Proveedor no encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)

            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        Proveedor p = proveedorService.findById(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }


    @Operation(
            summary = "Lista ProveedoresDTO",
            description = "Lista a todos los proveedoresDTO registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "ProveedoresDTO encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProveedorDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "204",
            description = "ProveedoresDTO no encontrados",
            content = @Content
    )
    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<ProveedorDTO> dto = proveedorService.listaDetallada();
        if(dto == null)return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Lista Proveedores",
            description = "Lista a todos los proveedores de los mismos paises registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Proveedores encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProveedorDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Proveedores no encontrados",
            content = @Content
    )
    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<?>> agruparPais(@PathVariable String pais) {
        return ResponseEntity.ok(proveedorService.findByPais(pais));
    }

    @Operation(
            summary = "Crear Proveedor",
            description = "Crea un Proveedor y lo guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Proveedor creado con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)

            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Proveedor no pudo ser creado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar (@Valid @RequestBody Proveedor p){
        Proveedor proveedor = proveedorService.save(p);
        return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Proveedor",
            description = "Busca al Proveedor por ID y lo elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Proveedor eliminado con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de Proveedor no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar un proveedor por ID",
            description = "Busca un proveedor existente por su ID y actualiza sus datos."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Proveedor actualizado con éxito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "El id del proveedor no se encontró",
            content = @Content
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor){
        Proveedor p = proveedorService.update(proveedor, id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }
}