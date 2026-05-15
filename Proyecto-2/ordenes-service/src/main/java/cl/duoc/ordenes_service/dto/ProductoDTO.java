package cl.duoc.ordenes_service.dto;


import lombok.Data;

@Data
public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private Double precio;
}
