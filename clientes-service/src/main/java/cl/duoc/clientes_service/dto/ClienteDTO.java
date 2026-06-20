package cl.duoc.clientes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String nombreCompleto;
    private String correo;
    private String nombreEmpresa;
}
