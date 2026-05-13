package cl.duoc.productos_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank (message = "El campo no debe estar vacio.")
    private String nombre;

    @NotBlank (message = "El campo no debe estar vacio.")
    @Column(name = "pais_origen")
    private String paisOrigen;


}
