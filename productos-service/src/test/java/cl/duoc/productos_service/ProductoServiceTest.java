package cl.duoc.productos_service;


import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.mapper.ProductoMapper;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.repository.MarcaRepository;
import cl.duoc.productos_service.repository.ProductoRepository;
import cl.duoc.productos_service.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias en ProductoService")
public class ProductoServiceTest {

        @Mock
        private ProductoRepository productoRepository;

        @Mock
        private MarcaRepository marcaRepository;

        @Mock
        private ProductoMapper mapper;

        @InjectMocks
        private ProductoService productoService; // Ajusta al nombre real de tu clase Service

        private Producto productoMock;
        private Marca marcaMock;

        @BeforeEach
        void setUp() {
            marcaMock = new Marca();
            marcaMock.setId(1L);
            marcaMock.setNombre("Sony");

            productoMock = new Producto();
            productoMock.setId(10L);
            productoMock.setNombre("Audífonos");
            productoMock.setMarca(marcaMock);
        }

        @Test
        @DisplayName("Debe retornar la lista completa de productos")
        void testFindAll() {
            Mockito.when(productoRepository.findAll()).thenReturn(List.of(productoMock));

            List<Producto> resultado = productoService.findAll();

            assertNotNull(resultado);
            assertEquals(1, resultado.size());
        }

        @Test
        @DisplayName("Debe retornar el producto cuando se busca por ID")
        void testFindById() {
            Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(productoMock));

            Producto resultado = productoService.findById(10L);

            assertNotNull(resultado);
            assertEquals("Audífonos", resultado.getNombre());
        }

        @Test
        @DisplayName("Debe guardar un producto asociando su marca")
        void testSave() {
            Mockito.when(marcaRepository.findById(1L)).thenReturn(Optional.of(marcaMock));
            Mockito.when(productoRepository.save(productoMock)).thenReturn(productoMock);

            Producto resultado = productoService.save(productoMock);

            assertNotNull(resultado);
            assertEquals("Sony", resultado.getMarca().getNombre());
        }

        @Test
        @DisplayName("Debe llamar al repositorio para eliminar por ID")
        void testDeleteById() {
            assertDoesNotThrow(() -> productoService.deleteById(10L));
            Mockito.verify(productoRepository, Mockito.times(1)).deleteById(10L);
        }

        @Test
        @DisplayName("Debe actualizar los datos de un producto existente")
        void testUpdate() {
            Producto productoModificado = new Producto();
            productoModificado.setNombre("Audífonos Bluetooth");
            productoModificado.setMarca(marcaMock);

            Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(productoMock));
            Mockito.when(marcaRepository.findById(1L)).thenReturn(Optional.of(marcaMock));
            Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenReturn(productoMock);

            Producto resultado = productoService.update(10L, productoModificado);

            assertNotNull(resultado);
            assertEquals("Audífonos Bluetooth", resultado.getNombre());
        }

        @Test
        @DisplayName("Debe retornar el DTO correspondiente al buscar por ID")
        void testBuscarDTO() {
            ProductoDTO dtoMock = new ProductoDTO();
            dtoMock.setId(10L);
            dtoMock.setNombre("Audífonos");

            Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(productoMock));
            Mockito.when(mapper.toDTO(productoMock)).thenReturn(dtoMock);

            ProductoDTO resultado = productoService.buscarDTO(10L);

            assertNotNull(resultado);
            assertEquals(10L, resultado.getId());
        }

    @Test
    @DisplayName("Debe reducir el stock correctamente cuando hay suficiente")
    void testReducirStock_Exitoso() {
        productoMock.setStock(10);
        productoMock.setPrecio(5000); // Evita problemas si el precio se usa en otra parte

        Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(productoMock));
        Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenReturn(productoMock);

        Producto resultado = productoService.reducirStock(10L, 4);

        assertNotNull(resultado);
        assertEquals(6, resultado.getStock()); // 10 - 4 = 6
    }

    @Test
    @DisplayName("Debe lanzar una RuntimeException cuando el stock es insuficiente")
    void testReducirStock_Insuficiente() {
        productoMock.setStock(2);

        Mockito.when(productoRepository.findById(10L)).thenReturn(Optional.of(productoMock));

        assertThrows(RuntimeException.class, () -> {
            productoService.reducirStock(10L, 5);
        });
    }

    @Test
    @DisplayName("Debe filtrar y retornar los productos de la categoría indicada")
    void testFindByCategoria() {
        productoMock.setCategoria("Electrónica");

        Producto productoDos = new Producto();
        productoDos.setCategoria("Hogar");

        Mockito.when(productoRepository.findAll()).thenReturn(List.of(productoMock, productoDos));

        List<Producto> resultado = productoService.findByCategoria("electrónica");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Electrónica", resultado.get(0).getCategoria());
    }

    @Test
    @DisplayName("Debe filtrar y retornar los productos dentro del rango de precio")
    void testFindByPrecioBetween() {
        productoMock.setPrecio(15000); // Entra en el rango

        Producto productoDos = new Producto();
        productoDos.setPrecio(50000); // Se pasa del rango

        Mockito.when(productoRepository.findAll()).thenReturn(List.of(productoMock, productoDos));

        List<Producto> resultado = productoService.findByPrecioBetween(10000, 20000);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(15000, resultado.get(0).getPrecio());
    }
}

