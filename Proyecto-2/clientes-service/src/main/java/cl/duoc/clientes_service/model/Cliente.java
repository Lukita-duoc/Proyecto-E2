package cl.duoc.clientes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    //fef

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;



    @NotNull
    @Positive(message = "El rut no puede ser negativo")
    @Column(nullable = false, unique = true)
    private int rut;

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

    @NotBlank(message = "El telefono no puede estar en blanco")
    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    @NotNull(message = "La empresa es obligatoria")
    private Empresa empresa;


}
