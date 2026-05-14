package cl.duoc.proveedores_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacto-proveedor")
public class ContactoProveedor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_contacto;

    @NotBlank(message = "El campo no debe estar vacío")
    @Column(nullable = false)
    private String nombre_contacto;

    @NotBlank(message = "El campo no debe estar vacío")
    @Column(nullable = false)
    private String cargo;

    @NotBlank(message = "El campo no debe estar vacío")
    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @NotNull(message = "El proveedor es obligatoria")
    private Proveedor proveedor;

}
