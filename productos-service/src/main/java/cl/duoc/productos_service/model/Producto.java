package cl.duoc.productos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre no debe estar vacio.")
    @Size(min = 3, max = 50, message = "El nombre de producto debe tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank (message = "La descripcion no debe estar vacio.")
    @Size(min = 3, max = 150, message = "La descripcion debe tener entre 3 y 150 caracteres")
    @Column(nullable = false)
    private String descripcion;

    @NotNull (message = "El precio no debe ser nulo")
    @Positive(message = "El precio no puede ser menor a 0")
    @Column(nullable = false)
    private Integer precio;

    @NotNull (message = "El stock no debe ser nulo")
    @Positive(message = "El Stock no puede ser menor a 0")
    @Column(nullable = false)
    private Integer stock;

    @NotBlank (message = "La categoria no debe estar vacio.")
    @Size(min = 3, max = 50, message = "La descripcion debe tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn( name = "id_marca")
    @NotNull (message = "La marca no debe ser nula")
    private Marca marca;
}
