package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.exception.ErrorResponse;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.service.MarcaService;
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
@RequestMapping("/api/v1/marcas")
@Tag(name = "Marcas", description = "Controlador para el CRUD de marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Operation(
            summary = "Lista a todas las marcas",
            description = "Metodo que muestra una lista de todas las marcas registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marcas encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = MarcaDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Marcas no encontradas",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Marca> m = marcaService.findAll();
        return ResponseEntity.ok(m);
    }

    @Operation(
            summary = "Busca marca",
            description = "Busca una marca a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marca Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MarcaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Marca no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Marca m = marcaService.findById(id);
        if(m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @Operation(
            summary = "Busca todas las marcas",
            description = "Retorna una lista DTO ."
    )
    @ApiResponse(
            responseCode = "200",
            description = "MarcaDTO encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MarcaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "MarcaDTO no encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @GetMapping("/listaDetallada")
    public ResponseEntity<List<?>> listarDTO(){
        List<MarcaDTO> dto = marcaService.listaDetallada();
        if(dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Crear Marcas",
            description = "Crea una Marca y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Marca creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MarcaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Marca no pudo ser creada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Marca m) {
        Marca marca = marcaService.save(m);
        if(marca == null) return new ResponseEntity<>(marca, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(marca, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Marcas",
            description = "Busca las marca por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Marca eliminada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la marca no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Marcas",
            description = "Busca las marca por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Marca actualizada con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MarcaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la marca no se encontro",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Marca marca) {
        Marca m = marcaService.update(id, marca);
        if(m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }
}