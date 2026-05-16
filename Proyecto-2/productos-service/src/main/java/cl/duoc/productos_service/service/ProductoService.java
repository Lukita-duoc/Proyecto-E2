package cl.duoc.productos_service.service;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.mapper.ProductoMapper;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.repository.MarcaRepository;
import cl.duoc.productos_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProductoMapper mapper;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto p) {
        Marca m = marcaRepository.findById(p.getMarca().getId()).orElse(null);
        p.setMarca(m);
        return productoRepository.save(p);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto update(Long id, Producto p) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null) return null;
        Marca m = marcaRepository.findById(p.getMarca().getId()).orElse(null);
        if (m == null) return null;

        producto.setNombre(p.getNombre());
        producto.setDescripcion(p.getDescripcion());
        producto.setPrecio(p.getPrecio());
        producto.setStock(p.getStock());
        producto.setCategoria(p.getCategoria());
        producto.setMarca(m);

        return productoRepository.save(producto);
    }


    public ProductoDTO buscarDTO(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null) return null;
        return mapper.toDTO(producto);
    }

    public Producto reducirStock(Long id, int cantidad) {
        Producto producto = productoRepository.findById(id).orElse(null);
        String mensaje = "stock insuficiente";
        if(producto == null) return null;
        int stockActualizado = producto.getStock() - cantidad;
        if(stockActualizado < 0) {
            throw new RuntimeException("Stock insuficiente: " + producto.getNombre()+ " Stock actual: " + producto.getStock());
        }
        producto.setStock(stockActualizado);
        return productoRepository.save(producto);
    }

    public List<Producto> findByCategoria(String categoria) {
        List<Producto> listaCategoria = new ArrayList<>();

        List<Producto> allProduct = productoRepository.findAll();

        for (Producto p : allProduct) {
            if(p.getCategoria() != null && p.getCategoria().equalsIgnoreCase(categoria)) {
                listaCategoria.add(p);
            }
        }

        return listaCategoria;
    }

    public List<Producto> findByPrecioBetween(int precioMin, int precioMax) {
        List<Producto> listaCategoria = new ArrayList<>();

        List<Producto> allProduct = productoRepository.findAll();

        for (Producto p : allProduct) {
            if(p.getPrecio() != null && p.getPrecio() >= precioMin && p.getPrecio() <= precioMax) {
                listaCategoria.add(p);
            }
        }
        return listaCategoria;
    }
}
