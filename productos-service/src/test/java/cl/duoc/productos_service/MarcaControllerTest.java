package cl.duoc.productos_service;

import cl.duoc.productos_service.controller.MarcaController;
import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.service.MarcaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MarcaController.class)
@DisplayName("Pruebas unitarias en MarcaController")
public class MarcaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MarcaService marcaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Marca marcaMock;
    private MarcaDTO marcaDtoMock;

    @BeforeEach
    void setUp() {
        marcaMock = new Marca();
        marcaMock.setId(1L);
        marcaMock.setNombre("Apple");
        marcaMock.setPaisOrigen("Chile");

        marcaDtoMock = new MarcaDTO();
        marcaDtoMock.setNombre("Apple");
        marcaDtoMock.setPaisOrigen("Chile");
    }

    @Test
    @DisplayName("GET / -> Debe retornar 200 OK con la lista de marcas")
    public void testListar() throws Exception{
        Mockito.when(marcaService.findAll()).thenReturn(Arrays.asList(new Marca(), new Marca()));

        mockMvc.perform(get("/api/v1/marcas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/v1/marcas/{id} -> Debe retornar 200 OK cuando la marca existe")
    void testBuscarId() throws Exception {
        Mockito.when(marcaService.findById(1L)).thenReturn(marcaMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/marcas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Apple"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paisOrigen").value("Chile"));
    }


    @Test
    @DisplayName("GET /api/v1/marcas/listaDetallada -> Debe retornar 200 OK y la lista de DTOs")
    void testListarDTO() throws Exception {
        Mockito.when(marcaService.listaDetallada()).thenReturn(List.of(marcaDtoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/marcas/listaDetallada")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Apple"));
    }

    @Test
    @DisplayName("POST / -> Debe retornar 201 Created y la marca guardada")
    void testGuardar() throws Exception {
        Mockito.when(marcaService.save(Mockito.any(Marca.class))).thenReturn(marcaMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/marcas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(marcaMock)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Apple"));
    }

    @Test
    @DisplayName("DELETE /{id} -> Debe retornar 204 No Content")
    void testEliminar() throws Exception {
        Mockito.doNothing().when(marcaService).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/marcas/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 200 OK y la marca modificada")
    void testActualizar() throws Exception {
        Mockito.when(marcaService.update(Mockito.eq(1L), Mockito.any(Marca.class))).thenReturn(marcaMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/marcas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(marcaMock)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Apple"));
    }


}
