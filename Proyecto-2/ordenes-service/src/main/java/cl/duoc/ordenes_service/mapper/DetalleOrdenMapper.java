package cl.duoc.ordenes_service.mapper;

import cl.duoc.ordenes_service.dto.DetalleOrdenDTO;
import cl.duoc.ordenes_service.model.DetalleOrden;
import org.springframework.stereotype.Component;

@Component
public class DetalleOrdenMapper {

    public DetalleOrdenDTO toDTO(DetalleOrden o){
        DetalleOrdenDTO dto = new DetalleOrdenDTO();

        dto.setProductoId(o.getProductoid());
        dto.setCantidad(o.getCantidad());
        //Necesito aqui ayuda
        return dto;
    }
}
