package cl.duoc.ordenes_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_orden")
public class DetalleOrden {

    @Id
    @Column(name = "id_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "la cantidad no debe ser nula")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "subtotal no debe ser nula")
    @PositiveOrZero(message = "subtotal no puede ser negativo")
    @Column(nullable = false)
    private Integer subtotal;

    @NotNull(message = "El Id de la orden no debe ser nula")
    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenCompra ordenId;

    @NotNull(message = "El Id del producto no debe ser nulas")
    @Column(name = "id_producto", nullable = false)
    private Long productoId;

}
