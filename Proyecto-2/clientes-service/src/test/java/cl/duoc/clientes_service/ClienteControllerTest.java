package cl.duoc.clientes_service;

import cl.duoc.clientes_service.controller.ClienteController;
import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(ClienteController.class)
@DisplayName("Pruebas unitarias en ClienteController")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    @Test
    @DisplayName("GET / listar - Debe retornar 200 OK con la lista de clientes")
    public void testListar() throws Exception {
        Mockito.when(clienteService.findAll()).thenReturn(Arrays.asList(new Cliente(), new Cliente()));

        mockMvc.perform(get("/api/v1/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /{id} -> Debe retornar 200 OK si el cliente existe")
    public void testBuscarId() throws Exception {
        Cliente cliente = new Cliente();
        Mockito.when(clienteService.findById(1L)).thenReturn(cliente);

        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /{id} -> Debe retornar 404 NOT FOUND si no existe")
    public void testBuscarIdNoExiste() throws Exception {
        Mockito.when(clienteService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/clientes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /buscardto/{id} -> Debe retornar 200 OK con el DTO")
    public void testListaDTO() throws Exception {
        Mockito.when(clienteService.buscarDTO(1L)).thenReturn(new ClienteDTO());

        mockMvc.perform(get("/api/v1/clientes/buscardto/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST / -> Debe retornar 201 CREATED al guardar")
    public void testGuardar() throws Exception {
        // Arrange

        cl.duoc.clientes_service.model.Empresa empresa = new cl.duoc.clientes_service.model.Empresa(
                1L,
                "Duoc UC",
                "Educación",
                "Metropolitana"
        );

        Cliente clienteInput = new Cliente(
                null,
                12345678,
                "Juan",
                "Pérez",
                "juan.perez@gmail.com",
                "987654321",
                empresa
        );

        Cliente clienteGuardado = new Cliente(
                1L,
                12345678,
                "Juan",
                "Pérez",
                "juan.perez@gmail.com",
                "987654321",
                empresa
        );

        Mockito.when(clienteService.save(any(Cliente.class))).thenReturn(clienteGuardado);

        // Act & Assert
        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInput)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /{id} -> Debe retornar 24 NO CONTENT")
    public void testBorrar() throws Exception {
        mockMvc.perform(delete("/api/v1/clientes/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(clienteService, Mockito.times(1)).delete(1L);
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 200 OK al actualizar con éxito")
    public void testUpdate() throws Exception {
        // Arrange
        cl.duoc.clientes_service.model.Empresa empresa = new cl.duoc.clientes_service.model.Empresa(
                1L,
                "Duoc UC",
                "Educación",
                "Metropolitana"
        );

        Cliente clienteInput = new Cliente(
                null,
                12345678,
                "Juan",
                "Pérez",
                "juan.perez@gmail.com",
                "987654321",
                empresa
        );

        Mockito.when(clienteService.update(any(Cliente.class), eq(1L))).thenReturn(clienteInput);

        // Act & Assert
        mockMvc.perform(put("/api/v1/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInput)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 404 NOT FOUND si no se puede actualizar")
    public void testUpdate_NoExiste() throws Exception {
        // Arrange
        cl.duoc.clientes_service.model.Empresa empresa = new cl.duoc.clientes_service.model.Empresa(
                1L,
                "Duoc UC",
                "Educación",
                "Metropolitana"
        );

        Cliente clienteInput = new Cliente(
                null,
                12345678,
                "Juan",
                "Pérez",
                "juan.perez@gmail.com",
                "987654321",
                empresa
        );

        Mockito.when(clienteService.update(any(Cliente.class), eq(99L))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/api/v1/clientes/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInput)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /rut/{rut} -> Debe retornar 200 OK si el rut existe")
    public void testBuscarPorRut_Existe() throws Exception {
        Mockito.when(clienteService.findByRut(12345678)).thenReturn(new Cliente());

        mockMvc.perform(get("/api/v1/clientes/rut/12345678"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /empresa/{empresaId} -> Debe retornar 200 OK con la lista por empresa")
    public void testBuscarPorEmpresa() throws Exception {
        Mockito.when(clienteService.findByIdempresa(1L)).thenReturn(Collections.singletonList(new Cliente()));

        mockMvc.perform(get("/api/v1/clientes/empresa/1"))
                .andExpect(status().isOk());
    }


}
