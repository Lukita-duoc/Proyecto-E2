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
    private Long id_proveedor;

    @NotBlank(message = "El campo no puede estar vacío")
    @Column(nullable = false)
    private String razon_social;

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
    @Column(nullable = false)
    private String tipo_proveedor;
}

