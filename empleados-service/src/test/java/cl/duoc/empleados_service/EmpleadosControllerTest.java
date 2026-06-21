package cl.duoc.empleados_service;

import cl.duoc.empleados_service.controller.EmpleadoController;
import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.service.EmpleadoService;
import org.assertj.core.util.Arrays;
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
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
@DisplayName("Pruebas unitarias en EmpleadoController")
public class EmpleadosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoService empleadoService;

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    private Empleado empleadoMock;
    private EmpleadoDTO empleadoDTOmock;
    private Empleado empleadoMock2;
    private Sucursal sucursalmock;

    @BeforeEach
    void setUp(){
        sucursalmock = new Sucursal();
        sucursalmock.setIdSucursal(1L);
        sucursalmock.setNombre("Splinter");
        sucursalmock.setCiudad("Santiago");
        sucursalmock.setCapacidad(2);

        empleadoMock = new Empleado();
        empleadoMock.setIdEmpleado(1L);
        empleadoMock.setNombre("Ignacio");
        empleadoMock.setApellido("Battistoni");
        empleadoMock.setCorreo("ignacio.battistonni@tander.cl");
        empleadoMock.setCargo("Jefe");
        empleadoMock.setFechaContrato(LocalDate.of(2026, 6, 18));
        empleadoMock.setSucursal(sucursalmock);

        empleadoMock2 = new Empleado();
        empleadoMock2.setIdEmpleado(2L);
        empleadoMock2.setNombre("Alexander");
        empleadoMock2.setApellido("Mejias");
        empleadoMock2.setCorreo("alexander.mejias@tander.cl");
        empleadoMock2.setCargo("Empleado");
        empleadoMock2.setFechaContrato(LocalDate.of(2026, 6, 18));
        empleadoMock2.setSucursal(sucursalmock);

        empleadoDTOmock = new EmpleadoDTO();
        empleadoDTOmock.setCorreo("tander.mejias@hola.cl");
        empleadoDTOmock.setCargo("Empleado");


    }

    @Test
    @DisplayName("GET / listar - Debe retornar 200 OK con la lista de Empleados")
    void testListar() throws Exception {
        Mockito.when(empleadoService.findAll()).thenReturn(List.of(empleadoMock));

        mockMvc.perform(get("/api/v1/empleados")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Ignacio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].apellido").value("Battistoni"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cargo").value("Jefe"));
    }


    @Test
    @DisplayName("GET /{id} - Debe retornar 200 OK si el empleado existe")
    void testBuscarId() throws Exception {
        Mockito.when(empleadoService.findById(1L)).thenReturn(empleadoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/empleados/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Ignacio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Battistoni"));
    }

    @Test
    @DisplayName("GET /{id} - Debe retornar 404 NOT FOUND si no existe")
    void testIdInexistente() throws Exception {
        Mockito.when(empleadoService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/empleados/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /listaDetallada - Debe retornar una lista DTO detallada")
    void testListaDetallada() throws Exception {
        Mockito.when(empleadoService.listaDetallada()).thenReturn(List.of(empleadoDTOmock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/empleados/listaDetallada")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cargo").value("Empleado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].correo").value("tander.mejias@hola.cl"));
    }

    @Test
    @DisplayName("GET /sucursal/{id} - debe retornar 200 OK con la lista por sucursañ")
    public void testBuscarSucursalID() throws Exception {
        Mockito.when(empleadoService.findByIdSucursal(1L)).thenReturn(Collections.singletonList(new Empleado()));

        mockMvc.perform(get("/api/v1/empleados/sucursal/1")).andExpect(status().isOk());

    }

    @Test
    @DisplayName("POST - / Debe retornar 201 CREATED al guardar")
    void testGuardar() throws  Exception{
        Mockito.when(empleadoService.save(Mockito.any(Empleado.class))).thenReturn(empleadoMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoMock)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Ignacio"));
    }

    @Test
    @DisplayName("DELETE /{id} - Debe borrar por ID")
    void testBorrar() throws Exception{
        Mockito.doNothing().when(empleadoService).deleteById(10L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/empleados/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("PUT /api/v1/empleados/{id} -> Debe retornar 200 OK y el empleado modificado")
    void testActualizar() throws Exception {
        Mockito.when(empleadoService.update(Mockito.eq(10L), Mockito.any(Empleado.class))).thenReturn(empleadoMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/empleados/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoMock)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
