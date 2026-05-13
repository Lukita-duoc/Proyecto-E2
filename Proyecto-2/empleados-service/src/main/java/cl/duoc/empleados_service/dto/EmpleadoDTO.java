package cl.duoc.empleados_service.dto;

import lombok.Data;

@Data
public class EmpleadoDTO {
    private String nombreCompleto;
    private String correo;
    private String cargo;
}
