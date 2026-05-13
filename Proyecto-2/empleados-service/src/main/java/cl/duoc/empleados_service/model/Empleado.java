package cl.duoc.empleados_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(nullable = false)
    private String nombre;
    @NotBlank(message = "El apellido no puede estar en blanco")
    @Column(nullable = false)
    private String apellido;
    @NotBlank(message = "El correo no puede estar en blanco")
    @Email(message = "Formato de correo invalido  ej: example@gmail.com")
    @Column(nullable = false, unique = true)
    private String correo;
    @NotBlank(message = "El cargo no puede estar en blanco")
    @Column(nullable = false)
    private String cargo;
    @NotNull(message = "EL contrato no puede ser nulo")
    @Column(nullable = false)
    private LocalDate fechaContrato;
    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false)
    @NotNull(message = "La sucursal es obligatoria")
    private Sucursal sucursal;
}
