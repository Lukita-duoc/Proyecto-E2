package cl.duoc.ordenes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "orden_compra")
public class OrdenCompra {

    @Id
    @Column(nullable = false, name = "id_orden")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotBlank (message = "El estado no debe estar vacio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 carácter")
    @Pattern(
            regexp = "^(PENDIENTE|PAGADO|ENVIADO|PROCESADO|CANCELADO)$",
            message = "El estado no es válido. Solo se permite: PENDIENTE, PAGADO, ENVIADO o CANCELADO"
    )
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "total no puede ser nulo")
    @Min(value = 0)
    @Column(nullable = false)
    private Integer total;

    @NotNull(message = "El id no debe ser nulo ")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

}
