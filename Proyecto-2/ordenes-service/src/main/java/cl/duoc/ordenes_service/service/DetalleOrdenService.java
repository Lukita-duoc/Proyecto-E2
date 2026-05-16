package cl.duoc.ordenes_service.service;

import cl.duoc.ordenes_service.dto.ProductoDTO;
import cl.duoc.ordenes_service.feign.ProductoFeign;
import cl.duoc.ordenes_service.model.DetalleOrden;
import cl.duoc.ordenes_service.model.OrdenCompra;
import cl.duoc.ordenes_service.repository.DetalleOrdenRepository;
import cl.duoc.ordenes_service.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public List<DetalleOrden> findAll() {
        return detalleOrdenRepository.findAll();
    }

    public DetalleOrden findById(Long id) {
        return detalleOrdenRepository.findById(id).orElse(null);
    }

    public DetalleOrden save(DetalleOrden d) {
        ProductoDTO p = productoFeign.buscarDTO(d.getProductoId());
        if(p == null) return null;
        return detalleOrdenRepository.save(d);
    }

    public void deleteById(Long id) {
        detalleOrdenRepository.deleteById(id);
    }

    public DetalleOrden update(Long id, DetalleOrden d) {
        DetalleOrden detalle = detalleOrdenRepository.findById(id).orElse(null);
        if(detalle == null) return null;

        detalle.setCantidad(d.getCantidad());
        detalle.setSubtotal(d.getSubtotal());
        detalle.setProductoId(d.getProductoId());

        return detalleOrdenRepository.save(detalle);
    }

    public DetalleOrden guardarDetalle(DetalleOrden detalle) {
        OrdenCompra orden = ordenCompraRepository.findById(detalle.getOrdenId().getId()).orElse(null);
        if(orden == null) return null;
        ProductoDTO producto = productoFeign.buscarDTO(detalle.getProductoId());
        if(producto == null) return null;
        double calculo = (double) (producto.getPrecio() * detalle.getCantidad());
        detalle.setSubtotal(calculo);
        DetalleOrden detalleGuardar = detalleOrdenRepository.save(detalle);
        List<DetalleOrden> todosDetalles = detalleOrdenRepository.findByOrdenId(orden);
        int suma = 0;
        for (DetalleOrden d : todosDetalles) {
            suma += d.getSubtotal().intValue();
        }
        orden.setTotal(suma);
        ordenCompraRepository.save(orden);

        return detalleGuardar;
    }


}
