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

    }

    @Test
    @DisplayName("GET / listar - Debe retornar 200 OK con la lista de Empleados")
    void testListar() throws Exception {
        Mockito.when(empleadoService.findAll()).thenReturn(List.of(new Empleado(), new Empleado()));

        mockMvc.perform(get("/api/v1/empleados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Ignacio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cargo").value("Jefe"));
    }


    @Test
    @DisplayName("GET /{id} - Debe retornar 200 OK si el empleado existe")
    void testBuscarId() throws Exception {
        Mockito.when(empleadoService.findAll()).thenReturn(List.of(empleadoMock));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/empleados/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Ignacio Battistoni"));
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
        Mockito.when(empleadoService.listaDetallada()).thenReturn(List.of(new EmpleadoDTO(), new EmpleadoDTO()));


    }




}
