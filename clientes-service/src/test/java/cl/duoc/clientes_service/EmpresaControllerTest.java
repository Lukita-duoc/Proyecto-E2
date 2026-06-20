package cl.duoc.clientes_service;

import cl.duoc.clientes_service.controller.EmpresaController;
import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.service.EmpresaService;
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

@WebMvcTest(EmpresaController.class)
@DisplayName("Pruebas unitarias en EmpresaController")
public class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpresaService empresaService;

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;


    @Test
    @DisplayName("GET / -> Debe retornar 200 OK con la lista de empresas")
    public void testListar() throws Exception {
        Mockito.when(empresaService.findAll()).thenReturn(Arrays.asList(new Empresa(), new Empresa()));

        mockMvc.perform(get("/api/v1/empresas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("GET /{id} -> Debe retornar 200 OK si la empresa existe")
    public void testBuscarPorId_Existe() throws Exception {
        Empresa empresa = new Empresa(1L, "Duoc UC", "Educación", "Metropolitana");
        Mockito.when(empresaService.findById(1L)).thenReturn(empresa);

        mockMvc.perform(get("/api/v1/empresas/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /{id} -> Debe retornar 404 NOT FOUND si la empresa no existe")
    public void testBuscarPorId_NoExiste() throws Exception {
        Mockito.when(empresaService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/empresas/99"))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("GET /listaDetallada -> Debe retornar 200 OK con la lista de DTOs")
    public void testListarDTO() throws Exception {
        Mockito.when(empresaService.listaDetallada()).thenReturn(Collections.singletonList(new EmpresaDTO()));

        mockMvc.perform(get("/api/v1/empresas/listaDetallada"))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("POST / -> Debe retornar 201 CREATED al guardar una empresa válida")
    public void testGuardar() throws Exception {

        Empresa empresaInput = new Empresa(null, "ProSkills", "Capacitación", "Valparaíso");
        Empresa empresaGuardada = new Empresa(1L, "ProSkills", "Capacitación", "Valparaíso");

        Mockito.when(empresaService.save(any(Empresa.class))).thenReturn(empresaGuardada);

        mockMvc.perform(post("/api/v1/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaInput)))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("DELETE /{id} -> Debe retornar 204 NO CONTENT")
    public void testEliminar() throws Exception {
        mockMvc.perform(delete("/api/v1/empresas/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(empresaService, Mockito.times(1)).delete(1L);
    }


    @Test
    @DisplayName("PUT /{id} -> Debe retornar 200 OK al actualizar con éxito")
    public void testUpdate_Existe() throws Exception {
        Empresa empresaInput = new Empresa(null, "Duoc UC Modificado", "Educación", "Metropolitana");
        Mockito.when(empresaService.update(any(Empresa.class), eq(1L))).thenReturn(empresaInput);

        mockMvc.perform(put("/api/v1/empresas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaInput)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /{id} -> Debe retornar 404 NOT FOUND si la empresa no existe")
    public void testUpdate_NoExiste() throws Exception {
        Empresa empresaInput = new Empresa(null, "Duoc UC Modificado", "Educación", "Metropolitana");
        Mockito.when(empresaService.update(any(Empresa.class), eq(99L))).thenReturn(null);

        mockMvc.perform(put("/api/v1/empresas/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaInput)))
                .andExpect(status().isNotFound());
    }
}
