package cl.duoc.proveedores_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacto_proveedores")
public class ContactoProveedor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long idContacto;

    @NotBlank(message = "El nombre de contacto no debe estar vacío")
    @Size(min = 3, max = 30, message = "nombre de contacto debe de tener entre 3 y 30 caracteres")
    @Column(nullable = false, name = "nombre_contacto")
    private String nombreContacto;

    @NotBlank(message = "El cargo no debe estar vacío")
    @Size(min = 3, max = 30, message = "cargo debe de tener entre 3 y 30 caracteres")
    @Column(nullable = false)
    private String cargo;

    @NotBlank(message = "El telefono no debe estar vacío")
    @Size(min = 9, max = 10,message = "telefono debe de tener 10 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El campo debe contener solo números")
    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @NotNull(message = "El proveedor no puede ser nulo")
    private Proveedor proveedor;

}
