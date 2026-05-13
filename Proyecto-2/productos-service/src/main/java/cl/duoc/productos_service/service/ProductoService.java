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

        producto.setNombre(p.getNombre());
        producto.setDescripcion(p.getDescripcion());
        producto.setPrecio(p.getPrecio());
        producto.setStock(p.getStock());
        producto.setCategoria(p.getCategoria());


        return productoRepository.save(producto);
    }

    public List<ProductoDTO> listaDetallada(){
        List<Producto> listado = productoRepository.findAll();
        List<ProductoDTO> listadoDTO = new ArrayList<>();

        for (Producto p : listado) {
            ProductoDTO dto = mapper.toDTO(p);
            listadoDTO.add(dto);
        }
        return listadoDTO;
    }
}
