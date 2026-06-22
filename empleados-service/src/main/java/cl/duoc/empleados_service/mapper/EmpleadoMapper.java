package cl.duoc.empleados_service.mapper;

import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoDTO toDTO(Empleado e) {
        if(e == null) return null;

        EmpleadoDTO dto = new EmpleadoDTO();

        dto.setNombreCompleto(e.getNombre() + " "+ e.getApellido());
        dto.setCorreo(e.getCorreo());
        dto.setCargo(e.getCargo());
        dto.setFechaContrato(e.getFechaContrato());
        dto.setSucursal(e.getSucursal());

        return dto;
    }
}
