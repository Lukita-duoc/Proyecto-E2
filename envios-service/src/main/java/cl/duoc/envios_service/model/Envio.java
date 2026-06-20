package cl.duoc.envios_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long id;
    @NotBlank(message = "El transportista no puede estar vacio")
    @Column(nullable = false)
    private String transportista;
    @NotBlank(message = "El origen no puede estar vacio")
    @Size(min = 3, max = 50, message = "el origen de destino tiene que tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String origen;
    @NotBlank(message = "El destino no puede estar vacio")
    @Size(min = 3, max = 50, message = "el destino tiene que tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String destino;
    @NotNull(message = "La fecha de salida no puede ser nula")
    @Column(nullable = false)
    private LocalDate fechaSalida;
    @NotBlank(message = "El estado de envio no puede estar vacio")
    @Size(min = 3, max = 50, message = "el estado de envio tiene que tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String estadoEnvio;
    @NotNull(message = "El id de orden no puede ser nulo")
    @Column(nullable = false)
    private Long ordenId;

}
