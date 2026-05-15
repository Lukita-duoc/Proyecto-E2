package cl.duoc.ordenes_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_orden")
public class DetalleOrden {

    @Column(name = "id_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El subtotal no debe estar vacío")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "El subtotal no debe estar vacío")
    @Column(nullable = false)
    private Double subtotal;

    @NotNull(message = "El Id de la orden no debe estar vacía")
    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenCompra ordenid;

    @NotNull(message = "El Id del producto no debe estar vacío")
    @Column(name = "id_producto", nullable = false)
    private Long productoid;

}
