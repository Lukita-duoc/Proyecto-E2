package cl.duoc.productos_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Marca {

    @Id
    private Long idMarca;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String nombre;

    @NotBlank (message = "El campo no debe estar vacio.")
    private String paisOrigen;


}
