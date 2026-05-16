package cl.duoc.productos_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long id;

    @NotBlank (message = "El nombre no debe estar vacio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank (message = "El pais de origen no debe estar vacio.")
    @Size(min = 3, max = 50, message = "El pais de origen debe tener entre 3 y 50 caracteres")
    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;


}
