package cl.duoc.productos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @NotBlank(message = "El campo no debe estar vacio.")
    private String nombre;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String descripcion;

    @NotNull (message = "El precio no debe ser nulo")
    @Positive(message = "El precio no puede ser menor a 0")
    private int precio;

    @NotNull (message = "El stock no debe ser nulo")
    @Positive(message = "El Stock no puede ser menor a 0")
    private int stock;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String categoria;

    @ManyToOne
    @JoinColumn( name = "id_marca")
    @NotNull (message = "Debe tener como minimo 1 valor.")
    private Marca marca;
}
