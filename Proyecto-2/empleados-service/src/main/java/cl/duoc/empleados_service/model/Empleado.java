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
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "No debe quedar en blanco")
    private String nombre;

    @NotBlank(message = "No debe quedar en blanco")
    private String apellido;

    @NotBlank(message = "No debe quedar en blanco")
    private Email email;

    @NotBlank(message = "No debe quedar en blanco")
    private String cargo;

    private LocalDate fechaContrato;

    @ManyToOne
    @NotNull(message = "no debe ser nulo el codigo de la cucursal")
    @JoinColumn(nullable = false, name = "idSucursal")
    private Sucursal idSucursal;

}
