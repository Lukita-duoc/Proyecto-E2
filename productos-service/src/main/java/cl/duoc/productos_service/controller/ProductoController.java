package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.exception.ErrorResponse;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.service.ProductoService;
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
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Controlador para el CRUD de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(
            summary = "Lista a todas las productos",
            description = "Metodo que muestra una lista de todas las productos registradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Productos encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProductoDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Productos no encontradas",
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
        List<Producto> p = productoService.findAll();
        return ResponseEntity.ok(p);
    }

    @Operation(
            summary = "Busca producto",
            description = "Busca una producto a travez de la ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Producto Encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrada",
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
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Producto p = productoService.findById(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @Operation(
            summary = "Busca por ID de productoDTO",
            description = "Retorna una clase de un productoDTO."
    )
    @ApiResponse(
            responseCode = "200",
            description = "ProductoDTO encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "ProductoDTO no encontrada",
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
    public ResponseEntity<?> listarDTO(@PathVariable Long id){
        ProductoDTO dto = productoService.buscarDTO(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Reducir stock de producto",
            description = "Busca una producto por ID y reduce su stock segun la cantidad indicada"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Stock reducido con éxito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "No se pudo reducir el stock",
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
    @GetMapping("/stock/{id}")
    public ResponseEntity<?> reducirStock(@PathVariable Long id, @RequestParam int cantidad) {
        Producto p = productoService.reducirStock(id, cantidad);
        if(p == null) return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Buscar Productos por categoria",
            description = "Busca todas las productos de la misma categoria"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Productos encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProductoDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Productos no encontradas",
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
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<?>> filtrarCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.findByCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @Operation(
            summary = "Filtrar productos por rango de precio",
            description = "Busca todas las productos cuyo precio se encuentre entre el minimo y maximo especificado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Productos encontradas",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProductoDTO.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Productos no encontradas",
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
    @GetMapping("/filtrarPrecio/{min}/{max}")
    public ResponseEntity<List<?>> precioEntre(@PathVariable int min, @PathVariable int max) {
        List<Producto> productos = productoService.findByPrecioBetween(min, max);
        return ResponseEntity.ok(productos);
    }

    @Operation(
            summary = "Crear Productos",
            description = "Crea una Producto y la guarda en la BD"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Producto creada con exito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Producto no pudo ser creada",
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Producto p) {
        Producto producto = productoService.save(p);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "eliminar Productos",
            description = "Busca las producto por ID y las elimina"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Producto eliminada con éxito.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la producto no se encontro",
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
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar Productos",
            description = "Busca las producto por ID y las actualiza"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Producto actualizada con éxito.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "el id de la producto no se encontro",
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
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        Producto p = productoService.update(id, producto);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }
}