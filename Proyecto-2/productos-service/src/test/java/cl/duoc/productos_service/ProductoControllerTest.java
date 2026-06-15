package cl.duoc.productos_service;

import cl.duoc.productos_service.controller.ProductoController;
import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto productoMock;
    private ProductoDTO productoDtoMock;
    private Marca marcaMock;

    @BeforeEach
    void setUp() {
        marcaMock = new Marca();
        marcaMock.setId(1L);
        marcaMock.setNombre("Sony");
        marcaMock.setPaisOrigen("Japón");

        productoMock = new Producto();
        productoMock.setId(10L);
        productoMock.setNombre("Audífonos WH-1000XM4");
        productoMock.setDescripcion("Audífonos con cancelación de ruido");
        productoMock.setPrecio(250000);
        productoMock.setStock(15);
        productoMock.setCategoria("Electrónica");
        productoMock.setMarca(marcaMock);

        productoDtoMock = new ProductoDTO();
        productoDtoMock.setId(10L);
        productoDtoMock.setNombre("Audífonos WH-1000XM4");
        productoDtoMock.setPrecio(250000);
    }

    @Test
    @DisplayName("GET /api/v1/productos -> Debe retornar 200 OK y la lista completa")
    void testListar() throws Exception {
        Mockito.when(productoService.findAll()).thenReturn(List.of(productoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Audífonos WH-1000XM4"));
    }

    @Test
    @DisplayName("GET /api/v1/productos/{id} -> Debe retornar 200 OK cuando el producto existe")
    void testBuscarId() throws Exception {
        Mockito.when(productoService.findById(10L)).thenReturn(productoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Audífonos WH-1000XM4"));
    }

    @Test
    @DisplayName("GET /api/v1/productos/listaDetallada/{id} -> Debe retornar 200 OK y el DTO")
    void testListarDTO() throws Exception {
        Mockito.when(productoService.buscarDTO(10L)).thenReturn(productoDtoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos/listaDetallada/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10L));
    }

    @Test
    @DisplayName("GET /api/v1/productos/stock/{id}?cantidad=5 -> Debe retornar 201 Created al reducir stock")
    void testReducirStock() throws Exception {
        productoMock.setStock(10);
        Mockito.when(productoService.reducirStock(10L, 5)).thenReturn(productoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos/stock/{id}", 10L)
                        .param("cantidad", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(10));
    }

    @Test
    @DisplayName("GET /api/v1/productos/categoria/{categoria} -> Debe retornar 200 OK y lista filtrada")
    void testFiltrarCategoria() throws Exception {
        Mockito.when(productoService.findByCategoria("Electrónica")).thenReturn(List.of(productoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos/categoria/{categoria}", "Electrónica")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].categoria").value("Electrónica"));
    }

    @Test
    @DisplayName("GET /api/v1/productos/filtrarPrecio/{min}/{max} -> Debe retornar 200 OK y lista en rango")
    void testPrecioEntre() throws Exception {
        Mockito.when(productoService.findByPrecioBetween(200000, 300000)).thenReturn(List.of(productoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos/filtrarPrecio/{min}/{max}", 200000, 300000)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].precio").value(250000));
    }

    @Test
    @DisplayName("POST /api/v1/productos -> Debe retornar 201 Created y el producto guardado")
    void testGuardar() throws Exception {
        Mockito.when(productoService.save(Mockito.any(Producto.class))).thenReturn(productoMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoMock)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Audífonos WH-1000XM4"));
    }

    @Test
    @DisplayName("DELETE /api/v1/productos/{id} -> Debe retornar 204 No Content")
    void testEliminar() throws Exception {
        Mockito.doNothing().when(productoService).deleteById(10L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/productos/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("PUT /api/v1/productos/{id} -> Debe retornar 200 OK y el producto modificado")
    void testActualizar() throws Exception {
        Mockito.when(productoService.update(Mockito.eq(10L), Mockito.any(Producto.class))).thenReturn(productoMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/productos/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoMock)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10L));
    }
}
