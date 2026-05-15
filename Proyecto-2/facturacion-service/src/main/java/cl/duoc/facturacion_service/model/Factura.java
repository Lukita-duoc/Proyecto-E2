package cl.duoc.facturacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaEmision;
    @NotBlank(message = "El metodo de pago no puede estar vacio")
    @Column(nullable = false)
    private String metodoPago;
    @NotNull(message = "El total no puede ser nulo")
    @Column(nullable = false)
    private int total;
    @NotNull(message = "El id cliente no puede ser nulo")
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
    @NotNull(message = "El id de la orden no puede ser nulo")
    @Column(name = "id_orden", nullable = false)
    private Long idOrden;
}
