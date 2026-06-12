package cl.duoc.clientes_service;


import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.mapper.ClienteMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.repository.ClienteRepository;
import cl.duoc.clientes_service.repository.EmpresaRepository;
import cl.duoc.clientes_service.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en ClienteService")
public class ClienteServiceTest {


    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteService clienteService;



    private Empresa empresaMock;
    private Cliente clienteMock1;
    private Cliente clienteMock2;

    @BeforeEach
    public void setUp() {
        empresaMock = new Empresa(1L, "Duoc UC", "Educación", "Metropolitana");

        clienteMock1 = new Cliente(1L, 123456789,"Juan", "Perez","juan@gmail.com" ,"12345678" , empresaMock);
        clienteMock2 = new Cliente(2L, 323456789,"carlos", "Perez","carlos@gmail.com" ,"12345678" , empresaMock);
    }

    @Test
    @DisplayName("Debe listar los clientes añadidos a la BD")
    public void testListar() {
        List<Cliente> testLista = Arrays.asList(clienteMock1, clienteMock2);
        Mockito.when(clienteRepository.findAll()).thenReturn(testLista);

        List<Cliente> resultado = clienteService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe retornar un cliente cuando el ID existe")
    public void testBuscarPorId() {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(clienteMock1));

        Cliente resultado = clienteService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_cliente());
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe retornar null cuando el ID no existe")
    public void testBuscarIDInexistente() {
        Mockito.when(clienteRepository.findById(30L)).thenReturn(java.util.Optional.empty());

        Cliente resultado = clienteService.findById(30L);

        assertNull(resultado, "Retorna null si el cliente no existe");
    }

    @Test
    @DisplayName("Debe guardar un cliente con éxito si el correo no existe")
    public void testGuardar() {

        // 1. Buscar si existe empresa
        Mockito.when(empresaRepository.findById(1L)).thenReturn(java.util.Optional.of(empresaMock));

        // 2. crear usuario
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock1);

        // Act
        Cliente resultado = clienteService.save(clienteMock1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe eliminar un cliente por ID")
    public void testEliminar() {

        // Act
        clienteService.delete(1L);

        // Assert: Verificamos que se ejecutó deleteById con el ID 1L
        Mockito.verify(clienteRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe actualizar los datos del cliente")
    public void testActualizar() {

        // 1. Buscar si existe el cliente original que se va a modificar
        Mockito.when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(clienteMock1));

        // 2. Simulamos el guardado de los cambios
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock1);

        // Act
        Cliente resultado = clienteService.update(clienteMock1, 1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe retornar null si el cliente a actualizar no existe")
    public void testActualizarNoExiste() {

        // El cliente a buscar no se encuentra
        Mockito.when(clienteRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        // Act
        Cliente resultado = clienteService.update(clienteMock1, 99L);

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar el DTO si el cliente por ID existe")
    public void testBuscarDTO() {

        // Arrange
        ClienteDTO dtoMock = new ClienteDTO(); // Creamos un DTO vacío de prueba
        Mockito.when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(clienteMock1));
        Mockito.when(mapper.toDTO(clienteMock1)).thenReturn(dtoMock);

        // Act
        ClienteDTO resultado = clienteService.buscarDTO(1L);

        // Assert
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar null en buscarDTO si el ID no existe")
    public void testBuscarDTO_NoExiste() {

        // Arrange
        Mockito.when(clienteRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        // Act
        ClienteDTO resultado = clienteService.buscarDTO(99L);

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe encontrar un cliente por su RUT")
    public void testBuscarPorRut() {

        // Arrange
        Mockito.when(clienteRepository.findByRut(123456789)).thenReturn(java.util.Optional.of(clienteMock1));

        // Act
        Cliente resultado = clienteService.findByRut(123456789);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe listar los clientes que pertenecen a una empresa específica")
    public void testListarPorIdEmpresa() {

        // Arrange
        List<Cliente> todosLosClientes = Arrays.asList(clienteMock1, clienteMock2);
        Mockito.when(clienteRepository.findAll()).thenReturn(todosLosClientes);

        // Act: Buscamos los clientes de la empresa 1L
        List<Cliente> resultado = clienteService.findByIdempresa(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

}
