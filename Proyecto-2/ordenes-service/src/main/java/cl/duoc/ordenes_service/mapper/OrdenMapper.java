package cl.duoc.ordenes_service.mapper;

import cl.duoc.ordenes_service.dto.DetalleOrdenDTO;
import cl.duoc.ordenes_service.dto.OrdenDTO;
import cl.duoc.ordenes_service.model.DetalleOrden;
import cl.duoc.ordenes_service.model.OrdenCompra;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrdenMapper {

    public OrdenDTO toDTO(OrdenCompra orden) {
        OrdenDTO dto = new OrdenDTO();

        dto.setIdOrden(orden.getId());
        dto.setFecha(orden.getFecha());
        dto.setEstado(orden.getEstado());
        dto.setTotal(orden.getTotal());
        dto.setClienteId(orden.getClienteId());
        return dto;
    }

    public DetalleOrdenDTO toDetalleDTO(DetalleOrden detalle) {
        DetalleOrdenDTO dto = new DetalleOrdenDTO();

        dto.setProductoId(detalle.getProductoId());
        dto.setCantidad(detalle.getCantidad());
        return dto;
    }







}
