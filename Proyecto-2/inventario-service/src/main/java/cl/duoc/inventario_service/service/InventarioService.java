package cl.duoc.inventario_service.service;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.dto.ProductoDTO;
import cl.duoc.inventario_service.dto.SucursalDTO;
import cl.duoc.inventario_service.feign.ProductoFeign;
import cl.duoc.inventario_service.feign.SucursalFeign;
import cl.duoc.inventario_service.mapper.InventarioMapper;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private SucursalFeign sucursalFeign;

    @Autowired
    private InventarioMapper mapper;

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Inventario findById(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public Inventario save(Inventario i) {
        ProductoDTO producto = productoFeign.buscarDTO(i.getProductoId());
        if(producto == null) return null;
        SucursalDTO sucursal = sucursalFeign.buscarDTO(i.getProductoId());
        if(sucursal == null) return null;
        return inventarioRepository.save(i);
    }

    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);
    }

    public Inventario update(Inventario i, Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if(inventario == null) return null;

        inventario.setStockActual(i.getStockActual());
        inventario.setStockMinimo(i.getStockMinimo());
        inventario.setUbicacion(i.getUbicacion());
        inventario.setProductoId(i.getProductoId());
        inventario.setSucursalId(i.getSucursalId());

        return inventarioRepository.save(inventario);
    }

    public InventarioDTO buscarDTO(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if(inventario == null) return null;
        InventarioDTO dto = mapper.toDTO(inventario);
        ProductoDTO producto = productoFeign.buscarDTO(inventario.getProductoId());
        if(producto != null) {
            dto.setNombreProducto(producto.getNombre());
        }
        SucursalDTO sucursal = sucursalFeign.buscarDTO(inventario.getSucursalId());
        if(sucursal != null) {
            dto.setNombreSucursal(sucursal.getNombreSucursal());
        }

        return dto;
    }
}
