package cl.duoc.proveedores_service.dto;

import lombok.Data;

@Data
public class ProveedorDTO {

    private String razon_social;
    private String correo;
    private String pais;
    private String tipo_proveedor;
    private String telefono;
}
