package cl.duoc.empleados_service.mapper;

import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.model.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public SucursalDTO toDTO(Sucursal s) {
        if(s == null) return null;

        SucursalDTO dto = new SucursalDTO();

        dto.setId(s.getIdSucursal());
        dto.setNombreSucursal(s.getNombre());
        dto.setCiudad(s.getCiudad());
        dto.setCapacidad(s.getCapacidad());

        return dto;
    }
}
