package cl.duoc.productos_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data


public class Producto {

    @Id
    @NotNull (message = "Debe tener como minimo 1 valor.")
    private Long idProducto;


    @NotBlank(message = "El campo no debe estar vacio.")
    private String nombre;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String descripcion;

    @NotNull (message = "Debe tener como minimo 1 valor.")
    private int precio;

    @NotNull (message = "Debe tener como minimo 1 valor.")
    private int stock;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String categoria;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idmarca")
    @NotNull (message = "Debe tener como minimo 1 valor.")
    private Marca idMarca;
}
