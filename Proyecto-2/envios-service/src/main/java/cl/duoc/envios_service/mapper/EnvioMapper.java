package cl.duoc.envios_service.mapper;

import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.model.Envio;
import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    public EnvioDTO toDTO(Envio e) {
        EnvioDTO dto = new EnvioDTO();

        dto.setIdEnvio(e.getId());
        dto.setTransportista(e.getTransportista());
        dto.setDestino(e.getDestino());
        dto.setEstadoEnvio(e.getEstadoEnvio());
        dto.setFechaSalida(e.getFechaSalida());
        dto.setOrdenId(e.getOrdenId());

        return dto;
    }
}
