package cl.duoc.empleados_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Min(value = 1, message = "La capacidad mínima de una sucursal debe ser de al menos 1 trabajador")
    @Max(value = 100, message = "una sucursal no puede superar los 100 trabajadores")
    @Column(nullable = false)
    private Integer capacidad;
}
