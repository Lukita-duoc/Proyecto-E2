package cl.duoc.proveedores_service.mapper;

import cl.duoc.proveedores_service.dto.ContactoProveedorDTO;
import cl.duoc.proveedores_service.model.ContactoProveedor;
import org.springframework.stereotype.Component;

@Component
public class ContactoProveedorMapper {
    public ContactoProveedorDTO toDTO(ContactoProveedor cop){
        if(cop == null)return null;

        ContactoProveedorDTO dto = new ContactoProveedorDTO();

        dto.setNombrecontacto(cop.getNombreContacto());
        dto.setCargo(cop.getCargo());
        dto.setTelefono(cop.getTelefono());
        dto.setTipoProveedor(cop.getProveedor().getTipoProveedor());

        return dto;
    }
}
