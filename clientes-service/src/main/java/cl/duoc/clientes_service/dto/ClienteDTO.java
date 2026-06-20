package cl.duoc.clientes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id_cliente;
    private Integer rut;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String datosEmpresa;
}
