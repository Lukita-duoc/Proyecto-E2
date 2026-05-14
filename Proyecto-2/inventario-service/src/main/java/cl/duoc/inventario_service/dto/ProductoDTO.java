package cl.duoc.inventario_service.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private int precio;
    private String categoria;
    private int stock;
}
