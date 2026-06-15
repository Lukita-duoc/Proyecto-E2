package cl.duoc.productos_service;

import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.mapper.MarcaMapper;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.repository.MarcaRepository;
import cl.duoc.productos_service.service.MarcaService;
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
@DisplayName("Pruebas unitarias en MarcaService")
public class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @Mock
    private MarcaMapper mapper;

    @InjectMocks
    private MarcaService marcaService;

    private Marca marcaMock1;
    private Marca marcaMock2;

    @BeforeEach
    public void setUp() {
        marcaMock1 = new Marca(1L, "Hellmans", "EEUU");
        marcaMock2 = new Marca(2L, "Samgsumg", "CHINA");
    }

    @Test
    @DisplayName("Debe listar todas las maras añadidas a la BD")
    public void testListar() {
        List<Marca> testLista = Arrays.asList(marcaMock1, marcaMock2);
        Mockito.when(marcaRepository.findAll()).thenReturn(testLista);

        List<Marca> resultado = marcaService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar una marca cuando el ID existe")
    public void testBuscarID() {
        Mockito.when(marcaRepository.findById(1L)).thenReturn(java.util.Optional.of(marcaMock1));

        Marca resultado = marcaService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Debe retornar null cuando el ID NO existe")
    public void testBuscarIDInexistente() {
        Mockito.when(marcaRepository.findById(30L)).thenReturn(java.util.Optional.empty());

        Marca resultado = marcaService.findById(30L);

        assertNull(resultado);
    }


    @Test
    @DisplayName("Debe guardar una marca")
    public void testGuardar() {

        Mockito.when(marcaRepository.save(any(Marca.class))).thenReturn(marcaMock1);

        Marca resultado = marcaService.save(marcaMock1);

        assertNotNull(resultado);
    }


    @Test
    @DisplayName("Debe eliminar una marca por ID")
    public void testEliminar() {
        marcaService.deleteById(1L);

        Mockito.verify(marcaRepository, Mockito.times(1)).deleteById(1L);
    }


    @Test
    @DisplayName("Debe actualizar los datos de la marca")
    public void testActualizar() {
        Mockito.when(marcaRepository.findById(1L)).thenReturn(java.util.Optional.of(marcaMock1));

        Mockito.when(marcaRepository.save(any(Marca.class))).thenReturn(marcaMock1);

        Marca resultado = marcaService.update(1L, marcaMock1);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar null si la marca a actualizar no existe")
    public void testActualizarNoExiste() {

        Mockito.when(marcaRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        Marca resultado = marcaService.update(99L, marcaMock1);

        assertNull(resultado);
    }


    @Test
    @DisplayName("Debe retornar el DTO si la marca por ID existe")
    public void testBuscarDTO() {

        MarcaDTO dtoMock1 = new MarcaDTO();
        List<MarcaDTO> listaDtoMock = List.of(dtoMock1);

        Mockito.when(marcaRepository.findAll()).thenReturn(List.of(marcaMock1));
        Mockito.when(mapper.toDTO(marcaMock1)).thenReturn(dtoMock1);

        List<MarcaDTO> resultado = marcaService.listaDetallada();

        assertNotNull(listaDtoMock);
    }
}
