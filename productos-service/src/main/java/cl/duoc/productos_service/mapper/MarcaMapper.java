package cl.duoc.productos_service.mapper;

import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.model.Marca;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {

    public MarcaDTO toDTO(Marca m) {
        if(m == null) return null;
        MarcaDTO dto = new MarcaDTO();

        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setPaisOrigen(m.getPaisOrigen());

        return dto;
    }
}
