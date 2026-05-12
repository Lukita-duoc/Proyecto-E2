package cl.duoc.empleados_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "sucursal")
public class Sucursal {

    @Id
    @Column(unique = true, nullable = false)
    private Long idSucursal;

    @NotBlank(message = "No puede quedar en blanco.")
    private String nombre;

    @NotBlank(message = "No puede quedar en blanco.")
    private String ciudad;

    @NotNull( message = "Debe contener al menos 1 de capacidad")
    @Size (min = 1)
    private int capacidad;


}
