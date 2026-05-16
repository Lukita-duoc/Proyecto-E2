package cl.duoc.proveedores_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @NotBlank(message = "La razon social no puede estar vacío")
    @Size(min = 3, max = 30, message = "razon social debe de tener entre 3 y 30 caracteres")
    @Column(nullable = false, name = "razon_social")
    private String razonSocial;

    @Email(message = "formato invalido de correo ej: example@gmail.com")
    @NotBlank(message = "El correo no puede estar en blanco")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "El teléfono no puede estar en blanco")
    @Size(min = 9, max = 10,message = "telefono debe de tener 10 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "El telefono debe contener solo números")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "El pais no puede estar en blanco")
    @Size(min = 3, max = 30, message = "razon social debe de tener entre 3 y 30 caracteres")
    @Column(nullable = false)
    private String pais;

    @NotBlank(message = "El tipo de proveedor no puede estar en blanco")
    @Column(nullable = false, name = "tipo_proveedor")
    private String tipoProveedor;
}

