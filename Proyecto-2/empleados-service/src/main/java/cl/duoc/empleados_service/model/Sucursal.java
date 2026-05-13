package cl.duoc.empleados_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursales")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;
    @NotBlank(message = "El nombre no puede estar blanco")
    @Column(nullable = false)
    private String nombre;
    @NotBlank(message = "La ciudad no puede estar en blanco")
    @Column(nullable = false)
    private String ciudad;
    @NotNull(message = "La capacidad no puede ser nula")
    @PositiveOrZero(message = "La capacidad no puede ser menor a 0")
    @Column(nullable = false)
    private int capacidad;
}
