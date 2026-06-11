package cl.duoc.envios_service.controller;

import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.model.Envio;
import cl.duoc.envios_service.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Errores:
200: salió bien (OK) Relacionado con el GET
201: Creado. Relacionado con el POST
204: No hay contenido, asi es como se confirma que algo se borró y no hay nada. Relacionado con el DELETE
404: BAD REQUEST (Quizás se introducieron malos datos)
400: Datos incorrectamente colocados

*/

@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envios", description = "Controlador para el CRUD de Envios.")

public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Envio> e = envioService.findAll();
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Buscar por ID el envío.",
            description = "Método que busca un producto a través del ID (Long)"
    )
    @ApiResponse(responseCode = "200",
            description = "Envío encontrado",
            content = @Content(schema = @Schema(implementation = EnvioDTO.class)))
    @ApiResponse(responseCode = "404",
        description = "Envío no encontrado",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Envio e = envioService.findById(id);
        if(e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Buscar por ID el detalle del envío.",
            description = "Método que busca el detalle del envío a través del ID (Long)"
    )
    @ApiResponse(responseCode = "200",
            description = "Detalle de envío encontrado",
            content = @Content(schema = @Schema(implementation = EnvioDTO.class)))
    @ApiResponse(responseCode = "404",
        description = "Detalle de envío no encontrado",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class ) ))
    @GetMapping("/detalle/{id}")
    public ResponseEntity<EnvioDTO> buscarPorIdDto(@PathVariable Long id) {
        EnvioDTO dto = envioService.buscarDTO(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Envio envio) {
        Envio e = envioService.save(envio);
        if(e == null) return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar el envío.",
            description = "Método que actualiza el envío a través del ID (Long)"
    )
    @ApiResponse(responseCode = "400",
            description = "Datos de actualización inválidos",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404",
            description = "Envío no encontrado para actualizar",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "200",
            description = "Envío actualizado correctamente",
            content = @Content(schema = @Schema(implementation = EnvioDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @Valid @RequestBody Envio envio) {
        Envio actualizado = envioService.update(id, envio);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @Operation(
            summary = "Eliminar por ID el envío.",
            description = "Método que busca eliminar un envío a través del ID (Long)"
    )
    @ApiResponse(responseCode = "404",
            description = "Producto no encontrado para eliminar",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "204",
            description = "Producto eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
