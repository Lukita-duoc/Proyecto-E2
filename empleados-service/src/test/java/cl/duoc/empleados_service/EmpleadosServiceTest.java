package cl.duoc.empleados_service;


import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.mapper.EmpleadoMapper;
import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.repository.EmpleadoRepository;
import cl.duoc.empleados_service.repository.SucursalRepository;
import cl.duoc.empleados_service.service.EmpleadoService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en EmpleadoService")
public class EmpleadosServiceTest {

    //Aquí se crean clones de los objetos para no usar datos vitales del sistema
    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private SucursalRepository sucursalRepository;

    //Colocamos solo el de empleado, ya que el TEST es solo del empleado. El de la sucursal estará en su respectivo test
    @Mock
    private EmpleadoMapper mapper;

    //Hace que los "clones" se inicien
    @InjectMocks
    private EmpleadoService empleadoService;


    //Creamos las entidades con su clase
    private Sucursal sucursalMock;
    private Empleado empleadoMock;
    private Empleado empleadoMock2;


    //Sirve para inicializar el metodo
    @BeforeEach
    public void setUp() {
        sucursalMock = new Sucursal(1L, "Splinter", "Macchu Picchu", 12);

        empleadoMock = new Empleado(1L, "Ignacio", "Battistoni", "ignacio.battistonni@tander.cl", "Jefe", LocalDate.of(2026, 6, 18), sucursalMock);

        empleadoMock2= new Empleado(2L, "Alexander", "Mejias", "alexander.mejias@tander.cl", "Empleado", LocalDate.of(2026, 6, 18), sucursalMock);}

    //Le dice al entorno que es un Test y hace que funcione como uno para el sistema
    @Test
    @DisplayName("Debe listar los empleados añadidos a la BD")
    public void testeListar(){
        List<Empleado> testLista = Arrays.asList(empleadoMock, empleadoMock2);
        Mockito.when(empleadoRepository.findAll()).thenReturn(testLista);

        List<Empleado> resultado = empleadoService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ignacio", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe retornar un empleado si el ID existe")
    public void testBuscarPorId(){
        Mockito.when(empleadoRepository.findById(1L)).thenReturn(java.util.Optional.of(empleadoMock));

        Empleado resultado = empleadoService.findById(1L);

        //El resultado de que no sea nulo será retornar el nombre y el ID creado en el test pero llamando a los atributos de la clase real.
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdEmpleado());
        assertEquals("Ignacio", resultado.getNombre());
    }

    //En este buscamos un ID que no exista testeando que funcione correctamente la sintaxis del error
    @Test
    @DisplayName("Debe retornar null cuando el ID no existe")
    public void testBuscarIDInexistente(){
        Mockito.when(empleadoRepository.findById(30L)).thenReturn(java.util.Optional.empty());

        Empleado resultado = empleadoService.findById(30L);

        assertNull(resultado, "Retorna Null si el Empleado no existe");

    }

    @Test
    @DisplayName("Debe guardar un empleado con éxisto si el correo no existe")
    public void testGuardar(){

        //1. Buscar si existe la sucursal
        Mockito.when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(sucursalMock));

        //2. Crear Empleado
        //Si existe la sucursal, se procede a la creación del empleado y guardarlo
        Mockito.when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoMock);

        // Act
        //Aquí se guarda en el Mock de empleado
        Empleado resultado = empleadoService.save(empleadoMock);

        //Assert
        assertNotNull(resultado);
        assertEquals("Ignacio", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe eliminar a un Empleado por ID")
    public void testEliminar(){

        //Act
        empleadoService.deleteById(1L);

        // Assert
        Mockito.verify(empleadoRepository, Mockito.times(1)).deleteById((1l));
    }

    @Test
    @DisplayName("Debe actualizar los datos del Empleado")
    public void testActualizar(){

        // 1. Se simula que la sucursal exista
        Mockito.when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(sucursalMock));

        // 2. Se busca al empleado
        Mockito.when(empleadoRepository.findById(1L)).thenReturn(java.util.Optional.of(empleadoMock));

        // 3. Aquí guardamos
        Mockito.when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoMock);

        // Act
        Empleado resultado = empleadoService.update(1L, empleadoMock);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ignacio", resultado.getNombre());

    }

    @Test
    @DisplayName("Debe retornar null si el empleado a actualizar no existe")
    public void testActualizarNoExiste(){

        //Si el cliente buscado no se encuentra, se lanza un "vacio"
        Mockito.when(empleadoRepository.findById(23L)).thenReturn(java.util.Optional.empty());

        //Act
        Empleado resultado = empleadoService.update(23L, empleadoMock);

        //Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Debe retornar la lista completa de Empleados DTO")
    public void testListaDetallada() {
        // Se Prepara el DTO de prueba
        EmpleadoDTO dtoMock = new EmpleadoDTO();
        dtoMock.setCargo("Jefe");
        dtoMock.setCorreo("ignacio.battistonni@tander.cl");
        dtoMock.setNombreCompleto("Ignacio Battistoni");


        //El empleado lo metemos en una lista creada aquí
        List<Empleado> lista = List.of(empleadoMock);

        Mockito.when(empleadoRepository.findAll()).thenReturn(lista);

        Mockito.when(mapper.toDTO(any(Empleado.class))).thenReturn(dtoMock);

        // 2. Se llama al metodo en una lista en vez de ocupar el ID
        List<EmpleadoDTO> resultado = empleadoService.listaDetallada();

        // 3. Se verifica que no venga nulo
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe listar los empleados que pertenecen a una Sucursal especifica")
    public void testListarPorIdSucursal(){

        //Arrange
        List<Empleado> todoEmpleado = Arrays.asList(empleadoMock, empleadoMock2);
        Mockito.when(empleadoRepository.findAll()).thenReturn((todoEmpleado));

        List<Empleado> resultado = empleadoService.findByIdSucursal(1L);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

    }



}
