package cl.duoc.empleados_service.dto;

import cl.duoc.empleados_service.model.Sucursal;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDTO {
    private Long idEmpleado;
    private String nombreCompleto;
    private String correo;
    private String cargo;
    private LocalDate fechaContrato;
    private Sucursal sucursal;
}
