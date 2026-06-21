package cl.duoc.envios_service;


import cl.duoc.envios_service.controller.EnvioController;
import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.model.Envio;
import cl.duoc.envios_service.service.EnvioService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnvioController.class)
@DisplayName("Prueba unitarias en EnviosController")
public class EnviosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnvioService envioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Envio envioMock;
    private EnvioDTO envioDTOMock;

    @BeforeEach
    void setUp(){
        envioMock = new Envio();
        envioMock.setId(1L);
        envioMock.setTransportista("Cristiano");
        envioMock.setOrigen("Estados Unidos");
        envioMock.setDestino("Portugal");
        envioMock.setFechaSalida(LocalDate.of(2026, 5, 16));
        envioMock.setEstadoEnvio("EN RUTA");
        envioMock.setOrdenId(1L);

        envioDTOMock = new EnvioDTO();
        envioDTOMock.setIdEnvio(1L);
        envioDTOMock.setTransportista("Cristiano");
        envioDTOMock.setDestino("Portugal");
        envioDTOMock.setFechaSalida(LocalDate.of(2026, 5, 16));
        envioDTOMock.setEstadoEnvio("EN RUTA");
        envioDTOMock.setOrdenId(1L);

    }

    @Test
    @DisplayName("GET /api/v1/envios  - Debe retornar 200 ok con la lista de Envio")
    public void testListar() throws Exception{
        Mockito.when(envioService.findAll()).thenReturn(Arrays.asList(new Envio(), new Envio()));

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/v1/envios/{id} -> Busca por ID y debe retornar 200 OK cuando el envio exista")
    void testBuscarId() throws Exception {
        Mockito.when(envioService.findById(1L)).thenReturn(envioMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/envios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportista").value("Cristiano"));
    }

    @Test
    @DisplayName("GET /api/v1/envios/detalle/{id} -> Debe retornar 200 OK y la lista de DTOs")
    void testListarDTO() throws Exception {
        Mockito.when(envioService.buscarDTO(1L)).thenReturn(envioDTOMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/envios/detalle/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportista").value("Cristiano"));
    }

    @Test
    @DisplayName("POST /{id} -> Debe retornar 201 Created y el envío guardado")
    void testGuardar() throws Exception {
        Mockito.when(envioService.save(Mockito.any(Envio.class))).thenReturn(envioMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envioMock)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportista").value("Cristiano"));
    }

    @Test
    @DisplayName("DELETE /{id} -> Debe retornar 204 No Content")
    void testEliminar() throws Exception {
        Mockito.doNothing().when(envioService).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/envios/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 200 OK y el envío modificado")
    void testActualizar() throws Exception {
        Mockito.when(envioService.update(Mockito.eq(1L), Mockito.any(Envio.class))).thenReturn(envioMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/envios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envioMock)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transportista").value("Cristiano"));
    }
    
    
}
