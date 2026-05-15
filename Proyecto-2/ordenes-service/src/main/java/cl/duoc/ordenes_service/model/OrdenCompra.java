package cl.duoc.ordenes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @NotBlank (message = "El campo no debe estar vacio")
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "El valor no debe ser 0")
    @Column(nullable = false)
    private Integer total;

    @NotNull(message = "El id no debe estar vacio ")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

}
