package cl.duoc.facturacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class    Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaEmision;
    @NotBlank(message = "El metodo de pago no puede estar vacio")
    @Size(min = 3, max = 15, message = "metodo de pago tiene que tener entre 3 y 15 caracteres")
    @Pattern(
            regexp = "^(TARJETA DE DEBITO|EFECTIVO|TARJETA DE CREDITO|TRANSFERENCIA|)$",
            message = "El metodo de pago no es válido. Solo se permite: TARJETA DE DEBITO, EFECTIVO, TARJETA DE CREDITO o TRANSFERENCIA"
    )
    @Column(nullable = false)
    private String metodoPago;
    @NotNull(message = "El total no puede ser nulo")
    @PositiveOrZero(message = "total no tiene que ser 0 o mayor que 0")
    @Column(nullable = false)
    private Integer total;
    @NotNull(message = "El id cliente no puede ser nulo")
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
    @NotNull(message = "El id de la orden no puede ser nulo")
    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @PrePersist
    protected void onCreate() {
        this.fechaEmision = LocalDateTime.now();
    }
}
