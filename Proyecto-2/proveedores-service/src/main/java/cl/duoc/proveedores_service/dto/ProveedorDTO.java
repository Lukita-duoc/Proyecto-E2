package cl.duoc.proveedores_service.dto;

import lombok.Data;

@Data
public class ProveedorDTO {

    private String razonSocial;
    private String correo;
    private String pais;
    private String tipoProveedor;
    private String telefono;
}
