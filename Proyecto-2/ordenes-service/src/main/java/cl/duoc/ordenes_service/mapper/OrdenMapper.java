package cl.duoc.ordenes_service.mapper;

import cl.duoc.ordenes_service.dto.DetalleOrdenDTO;
import cl.duoc.ordenes_service.dto.OrdenDTO;
import cl.duoc.ordenes_service.model.OrdenCompra;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrdenMapper {

    public OrdenDTO toDTO(OrdenCompra o) {
        OrdenDTO dto = new OrdenDTO();


        dto.setClienteId(o.getId());
        dto.setFecha(o.getFecha());
        dto.setEstado(o.getEstado());
        dto.setTotal(o.getTotal());
        dto.setClienteId(o.getClienteId());

        return dto;

    }

        //List<DetalleOrdenDTO> listaDetalles - Idea plus (va en public Orden DTO etc.)
        //dto.setDetalles(listaDetalles); - Idea plus (va al final de los dto's)
        //Habría que añadir en el ordenDTO: private List<DetalleOrdenDTO> detalles;







}
