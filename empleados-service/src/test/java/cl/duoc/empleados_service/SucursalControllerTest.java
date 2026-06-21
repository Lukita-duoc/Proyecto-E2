package cl.duoc.empleados_service;

import cl.duoc.empleados_service.controller.SucursalController;
import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.service.SucursalService;
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
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SucursalController.class)
@DisplayName("Pruebas unitarias en SucursalController")
public class SucursalControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private SucursalService sucursalService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Sucursal sucursalMock;
    private SucursalDTO sucursalDTOMock;
    
    @BeforeEach
    void setUp() {
        sucursalMock = new Sucursal();
        sucursalMock.setIdSucursal(1L);
        sucursalMock.setNombre("Splinter");
        sucursalMock.setCapacidad(25);
        sucursalMock.setCiudad("Santiago");
        
        sucursalDTOMock = new SucursalDTO();
        sucursalDTOMock.setId(1L);
        sucursalDTOMock.setNombreSucursal("Splinter");
        sucursalDTOMock.setCiudad("Santiago");
    }
    
    @Test
    @DisplayName("GET - Debe retornar 200 ok con la lista de Sucursales")
    public void testListar() throws Exception{
        Mockito.when(sucursalService.findAll()).thenReturn(Arrays.asList(new Sucursal(), new Sucursal()));

        mockMvc.perform(get("/api/v1/sucursales"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/v1/sucursales/{id} -> Debe retornar 200 OK cuando la sucursal exista")
    void testBuscarId() throws Exception {
        Mockito.when(sucursalService.findById(1L)).thenReturn(sucursalMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sucursales/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Splinter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ciudad").value("Santiago"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacidad").value(25));
    }
    @Test
    @DisplayName("GET /api/v1/sucursales/listaDetallada -> Debe retornar 200 OK y la lista de DTOs")
    void testListarDTO() throws Exception {
        Mockito.when(sucursalService.buscarDTO(1L)).thenReturn(sucursalDTOMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sucursales/listaDetallada/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreSucursal").value("Splinter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ciudad").value("Santiago"));
    }

    @Test
    @DisplayName("POST / -> Debe retornar 201 Created y la sucursal guardada")
    void testGuardar() throws Exception {
        Mockito.when(sucursalService.save(Mockito.any(Sucursal.class))).thenReturn(sucursalMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursalMock)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Splinter"));
    }

    @Test
    @DisplayName("DELETE /{id} -> Debe retornar 204 No Content")
    void testEliminar() throws Exception {
        Mockito.doNothing().when(sucursalService).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sucursales/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 200 OK y la sucursal modificada")
    void testActualizar() throws Exception {
        Mockito.when(sucursalService.update(Mockito.eq(1L), Mockito.any(Sucursal.class))).thenReturn(sucursalMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/sucursales/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursalMock)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Splinter"));
    }
    
    
    
    
    
}
