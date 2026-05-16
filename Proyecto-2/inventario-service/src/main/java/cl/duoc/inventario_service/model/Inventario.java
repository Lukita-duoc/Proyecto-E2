package cl.duoc.inventario_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario", nullable = false)
    private Long idInventario;
    @NotNull(message = "El stockActual no puede ser nulo")
    @Positive(message = "El stockActual no puede ser negativo")
    @Column(nullable = false)
    private int stockActual;
    @Min(1)
    @NotNull(message = "El stock minimo no puede ser nulo")
    @Column(nullable = false)
    private int stockMinimo;
    @NotBlank(message = "La ubicacion no puede estar en blanco")
    @Size(min = 3, max = 30, message = "la ubicacion debe tener entre 3 y 30 caracteres")
    @Column(nullable = false)
    private String ubicacion;
    @NotNull(message = "El producto_id no puede ser nulo")
    @Column(name = "producto_id", nullable = false)
    private Long productoId;
    @NotNull(message = "El sucursal_id no puede ser nulo")
    @Column(name = "sucursal_id", nullable = false)
    private Long sucursalId;
}
