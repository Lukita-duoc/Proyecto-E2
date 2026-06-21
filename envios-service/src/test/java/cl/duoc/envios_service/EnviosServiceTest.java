package cl.duoc.envios_service;

import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.dto.OrdenDTO;
import cl.duoc.envios_service.feign.OrdenFeign;
import cl.duoc.envios_service.mapper.EnvioMapper;
import cl.duoc.envios_service.model.Envio;
import cl.duoc.envios_service.repository.EnvioRepository;
import cl.duoc.envios_service.service.EnvioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en EnviosService")
public class EnviosServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private EnvioMapper mapper;

    @Mock
    private OrdenFeign ordenFeign;

    @InjectMocks
    private EnvioService envioService;

    private Envio envioMock;

    private EnvioDTO envioDTOMock;

    @BeforeEach
    void setUp() {

        envioMock = new Envio();
        envioMock.setId(1L);
        envioMock.setTransportista("Cristiano");
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
    @DisplayName("Debe listar los envios añadidos a la BD")
    public void testListar() {
        List<Envio> testLista = List.of(envioMock);

        Mockito.when(envioRepository.findAll()).thenReturn(testLista);

        List<Envio> resultado = envioService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Cristiano", resultado.get(0).getTransportista());
    }

    @Test
    @DisplayName("Debe retornar un envio cuando el ID existe")
    public void testBuscarPorId() {
        Mockito.when(envioRepository.findById(1L)).thenReturn(java.util.Optional.of(envioMock));

        Envio resultado = envioService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cristiano", resultado.getTransportista());
    }

    @Test
    @DisplayName("Debe retornar null cuando el ID no existe")
    public void testBuscarIDInexistente() {
        Mockito.when(envioRepository.findById(30L)).thenReturn(java.util.Optional.empty());

        Envio resultado = envioService.findById(30L);

        assertNull(resultado, "Retorna Null si el Envio no existe");

    }

    @Test
    @DisplayName("Debe guardar un envío con éxito")
    void testGuardarEnvio() {

        Mockito.when(ordenFeign.buscarPorIdDTO(anyLong())).thenReturn(new OrdenDTO());

        Mockito.when(envioRepository.save(any(Envio.class))).thenReturn(envioMock);


        Envio resultado = envioService.save(envioMock);

        assertNotNull(resultado);
        assertEquals("Cristiano", resultado.getTransportista());
    }

    @Test
    @DisplayName("Debe eliminar un envio por ID")
    public void testEliminar() {

        envioService.deleteById(1L);

        Mockito.verify(envioRepository, Mockito.times(1)).deleteById(1L);
    }
    
    @Test
    @DisplayName("Debe actualizar los datos del envio")
    public void testActualizar() {


        Mockito.when(envioRepository.findById(1L)).thenReturn(java.util.Optional.of(envioMock));


        Mockito.when(envioRepository.save(any(Envio.class))).thenReturn(envioMock);

        // Act
        Envio resultado = envioService.update(1L, envioMock);

        assertNotNull(resultado);
        assertEquals("Cristiano", resultado.getTransportista());
    }

    @Test
    @DisplayName("Debe retornar null si el envio a actualizar no existe")
    public void testActualizarNoExiste() {

        // El envio a buscar no se encuentra
        Mockito.when(envioRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        // Act
        Envio resultado = envioService.update(99L, envioMock);

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar el DTO si el envio por ID existe")
    public void testBuscarDTO() {

        // Arrange
        EnvioDTO dtoMock = new EnvioDTO(); // Creamos un DTO vacío de prueba
        Mockito.when(envioRepository.findById(1L)).thenReturn(java.util.Optional.of(envioMock));
        Mockito.when(mapper.toDTO(envioMock)).thenReturn(dtoMock);

        // Act
        EnvioDTO resultado = envioService.buscarDTO(1L);

        // Assert
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar null en buscarDTO si el ID no existe")
    public void testBuscarDTO_NoExiste() {

        // Arrange
        Mockito.when(envioRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        // Act
        EnvioDTO resultado = envioService.buscarDTO(99L);

        // Assert
        assertNull(resultado);
    }

}
