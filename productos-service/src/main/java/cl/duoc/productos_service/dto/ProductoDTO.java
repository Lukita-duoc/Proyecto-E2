package cl.duoc.productos_service.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private String categoria;
    private String nombreMarca;
}
