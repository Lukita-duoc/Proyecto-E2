package cl.duoc.clientes_service;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.mapper.EmpresaMapper;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.repository.EmpresaRepository;
import cl.duoc.clientes_service.service.EmpresaService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en EmpresaService")
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private EmpresaMapper mapper;

    @InjectMocks
    private EmpresaService empresaService;

    private Empresa empresaMock1;
    private Empresa empresaMock2;

    @BeforeEach
    public void setUp() {
        //  Empresas ficticias para usar en los tests
        empresaMock1 = new Empresa(1L, "Duoc UC", "Educación", "Metropolitana");
        empresaMock2 = new Empresa(2L, "ProSkills", "Capacitación", "Valparaíso");
    }

    @Test
    @DisplayName("Debe listar todas las empresas registradas")
    public void testFindAll() {
        Mockito.when(empresaRepository.findAll()).thenReturn(Arrays.asList(empresaMock1, empresaMock2));

        List<Empresa> resultado = empresaService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Duoc UC", resultado.get(0).getNombreEmpresa());
    }

    @Test
    @DisplayName("Debe retornar la empresa si el ID existe")
    public void testFindById() {
        Mockito.when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresaMock1));

        Empresa resultado = empresaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Duoc UC", resultado.getNombreEmpresa());
    }

    @Test
    @DisplayName("Debe retornar null si el ID de la empresa no existe")
    public void testFindByIdNoExiste() {
        Mockito.when(empresaRepository.findById(99L)).thenReturn(Optional.empty());

        Empresa resultado = empresaService.findById(99L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe guardar una empresa")
    public void testSave() {
        Mockito.when(empresaRepository.save(any(Empresa.class))).thenReturn(empresaMock1);

        Empresa resultado = empresaService.save(empresaMock1);

        assertNotNull(resultado);
        assertEquals("Duoc UC", resultado.getNombreEmpresa());
    }

    @Test
    @DisplayName("Debe eliminar la empresa")
    public void testDelete() {
        empresaService.delete(1L);
        Mockito.verify(empresaRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe actualizar los datos de la empresa si existe")
    public void testUpdate() {
        Mockito.when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresaMock1));
        Mockito.when(empresaRepository.save(any(Empresa.class))).thenReturn(empresaMock1);

        Empresa resultado = empresaService.update(empresaMock2, 1L);

        assertNotNull(resultado);
        Mockito.verify(empresaRepository, Mockito.times(1)).save(any(Empresa.class));
    }

    @Test
    @DisplayName("Debe retornar null si la empresa no existe")
    public void testUpdateNoExiste() {
        Mockito.when(empresaRepository.findById(99L)).thenReturn(Optional.empty());

        Empresa resultado = empresaService.update(empresaMock1, 99L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar una lista de EmpresaDTO")
    public void testListaDetallada() {
        // Arrange
        Mockito.when(empresaRepository.findAll()).thenReturn(Arrays.asList(empresaMock1, empresaMock2));

        EmpresaDTO dtoMock1 = new EmpresaDTO();
        EmpresaDTO dtoMock2 = new EmpresaDTO();

        Mockito.when(mapper.toDTO(empresaMock1)).thenReturn(dtoMock1);
        Mockito.when(mapper.toDTO(empresaMock2)).thenReturn(dtoMock2);

        // Act
        List<EmpresaDTO> resultado = empresaService.listaDetallada();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        Mockito.verify(mapper, Mockito.times(2)).toDTO(any(Empresa.class));
    }
}
