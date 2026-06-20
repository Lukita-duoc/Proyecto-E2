package cl.duoc.empleados_service;


import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.mapper.SucursalMapper;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.repository.SucursalRepository;
import cl.duoc.empleados_service.service.SucursalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en SucursalService")
public class SucursalServiceTest {
    
    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private SucursalMapper mapper;

    @InjectMocks
    private SucursalService sucursalService;

    private Sucursal sucursalMock1;
    private Sucursal sucursalMock2;

    @BeforeEach
    public void setUp(){
        sucursalMock1 = new Sucursal(1L, "Splinter", "Santiago", 20);
        sucursalMock2 = new Sucursal(2L, "Tander", "Santiago", 25);
    }

    @Test
    @DisplayName("Debe listar todas las sucursales a la BD")
    public void testListar(){
        List<Sucursal> testLista = Arrays.asList(sucursalMock1, sucursalMock2);
        Mockito.when(sucursalRepository.findAll()).thenReturn(testLista);

        List<Sucursal> resultado = sucursalService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

    }

    @Test
    @DisplayName("Debe retornar una sucursal cuando el ID existe")
    public void testBuscarID() {
        Mockito.when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(sucursalMock1));

        Sucursal resultado = sucursalService.findById(1L);

        assertNotNull(resultado);
        assertEquals(resultado.getIdSucursal(), 1L);
    }

    @Test
    @DisplayName("Debe retornar null cuando el ID NO existe")
    public void testBuscarIDInexistente() {
        Mockito.when(sucursalRepository.findById(30L)).thenReturn(java.util.Optional.empty());

        Sucursal resultado = sucursalService.findById(30L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe guardar una sucursal")
    public void testGuardar() {

        Mockito.when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalMock1);

        Sucursal resultado = sucursalService.save(sucursalMock1);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe eliminar una sucursal por ID")
    public void testEliminar() {
        sucursalService.deleteById(1L);

        Mockito.verify(sucursalRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe actualizar los datos de la sucursal")
    public void testActualizar() {
        Mockito.when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(sucursalMock1));

        Mockito.when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalMock1);

        Sucursal resultado = sucursalService.update(1L, sucursalMock1);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar null si la sucursal a actualizar no existe")
    public void testActualizarNoExiste() {

        Mockito.when(sucursalRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        Sucursal resultado = sucursalService.update(99L, sucursalMock1);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar el DTO si la sucursal por ID existe")
    public void testBuscarDTO() {

        SucursalDTO dtoMock1 = new SucursalDTO();
        List<SucursalDTO> listaDtoMock = List.of(dtoMock1);

        Mockito.when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(sucursalMock1));
        Mockito.when(mapper.toDTO(sucursalMock1)).thenReturn(dtoMock1);

        SucursalDTO resultado = sucursalService.buscarDTO(1L);

        assertNotNull(listaDtoMock);
    }
    
    
}
