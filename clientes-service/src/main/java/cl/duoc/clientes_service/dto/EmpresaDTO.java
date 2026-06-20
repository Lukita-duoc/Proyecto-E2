package cl.duoc.clientes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
    private Long empresaId;
    private String nombreEmpresa;
    private String tipoEmpresa;
    private String region;
}
