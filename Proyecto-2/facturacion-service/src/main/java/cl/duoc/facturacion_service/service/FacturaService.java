package cl.duoc.facturacion_service.service;

import cl.duoc.facturacion_service.dto.ClienteDTO;
import cl.duoc.facturacion_service.dto.DetalleOrdenDTO;
import cl.duoc.facturacion_service.dto.FacturaDTO;
import cl.duoc.facturacion_service.dto.OrdenesDTO;
import cl.duoc.facturacion_service.feign.ClienteFeign;
import cl.duoc.facturacion_service.feign.OrdenFeign;
import cl.duoc.facturacion_service.mapper.FacturaMapper;
import cl.duoc.facturacion_service.model.Factura;
import cl.duoc.facturacion_service.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaMapper mapper;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private OrdenFeign ordenFeign;

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura save(Factura f) {
        ClienteDTO cliente = clienteFeign.listaDTO(f.getIdCliente());
        if(cliente == null) return null;

        OrdenesDTO orden = ordenFeign.buscarDTO(f.getIdOrden());
        if(orden == null) return null;

        f.setTotal(orden.getTotal());
        return facturaRepository.save(f);
    }

    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    public FacturaDTO buscarDTO(Long id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        if(factura == null) return null;

        FacturaDTO dto = mapper.toDTO(factura);

        OrdenesDTO orden = ordenFeign.buscarDTO(factura.getIdOrden());

        if(orden != null && orden.getDetalle() != null) {
            List<String> listaNombre = new ArrayList<>();

            for (DetalleOrdenDTO detalle : orden.getDetalle()) {
                listaNombre.add(detalle.getNombre());
            }
            dto.setNombresProductos(listaNombre);
        }
        return dto;
    }

    public List<Factura> findByIdCliente(Long idCliente) {
        return facturaRepository.findByIdCliente(idCliente);
    }
}
