package cl.duoc.inventario_service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SucursalDTO {
    private Long id;
    private String nombreSucursal;
}
