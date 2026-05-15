package cl.duoc.facturacion_service.mapper;

import cl.duoc.facturacion_service.dto.FacturaDTO;
import cl.duoc.facturacion_service.model.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public FacturaDTO toDTO(Factura f) {
        FacturaDTO dto = new FacturaDTO();
        if(dto == null) return null;

        dto.setIdFactura(f.getId());
        dto.setFechaEmision(f.getFechaEmision());
        dto.setMetodoPago(f.getMetodoPago());
        dto.setTotal(f.getTotal());
        dto.setIdCliente(f.getIdCliente());
        dto.setIdOrden(f.getIdOrden());

        return dto;
    }
}
