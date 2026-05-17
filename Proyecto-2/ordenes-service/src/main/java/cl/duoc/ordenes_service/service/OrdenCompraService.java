package cl.duoc.ordenes_service.service;

import cl.duoc.ordenes_service.dto.ClienteDTO;
import cl.duoc.ordenes_service.dto.DetalleOrdenDTO;
import cl.duoc.ordenes_service.dto.OrdenDTO;
import cl.duoc.ordenes_service.dto.ProductoDTO;
import cl.duoc.ordenes_service.feign.ClienteFeign;
import cl.duoc.ordenes_service.feign.ProductoFeign;
import cl.duoc.ordenes_service.mapper.OrdenMapper;
import cl.duoc.ordenes_service.model.DetalleOrden;
import cl.duoc.ordenes_service.model.OrdenCompra;
import cl.duoc.ordenes_service.repository.DetalleOrdenRepository;
import cl.duoc.ordenes_service.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenRepository;

    @Autowired
    private DetalleOrdenRepository detalleRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private OrdenMapper mapper;

    public List<OrdenCompra> findAll() {return ordenRepository.findAll();}

    public OrdenCompra findById(Long id) {return ordenRepository.findById(id).orElse(null);}

    public OrdenCompra save(OrdenCompra o){
        ClienteDTO cliente = clienteFeign.buscarDTO(o.getClienteId());
        if(cliente == null) return null;
        o.setTotal(0);
        return ordenRepository.save(o);
    }



    public void deleteById(Long id) {
        ordenRepository.deleteById(id);
    }

    public OrdenCompra update(OrdenCompra o, Long id){
        OrdenCompra ordenCompra = ordenRepository.findById(id).orElse(null);
        if(ordenCompra == null)return null;

        ordenCompra.setClienteId(o.getClienteId());
        ordenCompra.setTotal(o.getTotal());
        ordenCompra.setEstado(o.getEstado());

        return ordenRepository.save(ordenCompra);
    }

    public OrdenDTO buscarDTO(Long id) {
        OrdenCompra orden = ordenRepository.findById(id).orElse(null);
        if (orden == null) return null;
        OrdenDTO dto = mapper.toDTO(orden);

        List<DetalleOrden> detalle = detalleRepository.findByOrdenId(orden);
        List<DetalleOrdenDTO> listaDTO = new ArrayList<>();

        for (DetalleOrden d : detalle) {
            DetalleOrdenDTO deto = mapper.toDetalleDTO(d);
            ProductoDTO prod = productoFeign.buscarId(d.getProductoId());
            if (prod != null) {
                deto.setNombre(prod.getNombre());
            }
            listaDTO.add(deto);
        }

        dto.setDetalle(listaDTO);
        return dto;
    }

}
