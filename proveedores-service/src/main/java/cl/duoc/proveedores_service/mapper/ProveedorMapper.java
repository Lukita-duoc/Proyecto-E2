package cl.duoc.proveedores_service.mapper;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor p){
        if(p == null) return null;
        ProveedorDTO dto = new ProveedorDTO();
        dto.setRazonSocial(p.getRazonSocial());
        dto.setCorreo(p.getCorreo());
        dto.setPais(p.getPais());
        dto.setTipoProveedor(p.getTipoProveedor());
        dto.setTelefono(p.getTelefono());

        return dto;
    }
}
