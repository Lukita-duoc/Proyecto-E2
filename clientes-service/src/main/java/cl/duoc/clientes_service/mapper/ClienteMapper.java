package cl.duoc.clientes_service.mapper;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente c) {
        if(c == null) return null;
        ClienteDTO dto = new ClienteDTO();

        dto.setId_cliente(c.getId_cliente());
        dto.setRut(c.getRut());
        dto.setNombreCompleto(c.getNombre()+ " " +c.getApellido());
        dto.setCorreo(c.getCorreo());
        dto.setTelefono(c.getTelefono());
        dto.setDatosEmpresa(c.getEmpresa().getNombreEmpresa());

        return dto;
    }
}

