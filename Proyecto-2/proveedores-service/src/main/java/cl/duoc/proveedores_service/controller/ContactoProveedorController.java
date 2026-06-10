package cl.duoc.proveedores_service.controller;

import cl.duoc.proveedores_service.dto.ContactoProveedorDTO;
import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.exception.ErrorResponse;
import cl.duoc.proveedores_service.model.ContactoProveedor;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.service.ContactoProveedorService;
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
@RequestMapping("/api/v1/contactoproveedores")
public class ContactoProveedorController {

    @Autowired
    private ContactoProveedorService conProService;

    @Operation(
            summary = "Lista a  Contacto de los Proveedores",
            description = "Lista a todos los Contacto de los proveedores registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = " Contacto de los Proveedores encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ContactoProveedorDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Contacto de los Proveedores no encontrados",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<?>> listar(){
        List<ContactoProveedor> cop = conProService.findAll();
        return ResponseEntity.ok(cop);
    }

    @Operation(
            summary = "Busca a un Contacto del Proveedor",
            description = "Busca un Contacto del Proveedor a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Contacto del Proveedor Encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ContactoProveedorDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Contacto del Proveedor no encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)

            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        ContactoProveedor cop = conProService.findById(id);
        if(cop == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cop);
    }

    @Operation(
            summary = "Lista de Contacto de los ProveedoresDTO",
            description = "Lista a todos los Contacto de los ProveedoresDTO registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = " Contacto de los ProveedoresDTO encontrados",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ContactoProveedorDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "204",
            description = " Contacto de los ProveedoresDTO no encontrados",
            content = @Content
    )
    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<ContactoProveedorDTO> dto = conProService.listaDetallada();
        if(dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Crear Contacto del Proveedor",
            description = "Crea un Contacto del Proveedor y lo guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Contacto del Proveedor creado con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ContactoProveedorDTO.class)

            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Contacto del Proveedor no pudo ser creado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ContactoProveedor cop){
        ContactoProveedor contactoProveedor = conProService.save(cop);
        return new ResponseEntity<>(contactoProveedor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Contacto del Proveedor",
            description = "Busca al Contacto del Proveedor por ID y lo elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Contacto del Proveedor eliminado con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de Contacto del Proveedor no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        conProService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar un Contacto del proveedor por ID",
            description = "Busca un Contacto del proveedor existente por su ID y actualiza sus datos."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Contacto del Proveedor actualizado con éxito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ContactoProveedorDTO.class)
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
            description = "El id del Contacto del proveedor no se encontró",
            content = @Content
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody ContactoProveedor contactoProveedor) {
        ContactoProveedor cop = conProService.update(contactoProveedor, id);
        if (cop == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cop);
    }
}
