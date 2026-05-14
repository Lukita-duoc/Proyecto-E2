package cl.duoc.proveedores_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Column(nullable = false, name = "razon_social")
    private String razonSocial;

    @Email
    @NotBlank(message = "El correo no puede estar en blanco")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "El teléfono no puede estar en blanco")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "El campo no puede estar en blanco")
    @Column(nullable = false)
    private String pais;

    @NotBlank(message = "El campo no puede estar en blanco")
    @Column(nullable = false, name = "tipo_proveedor")
    private String tipoProveedor;
}

