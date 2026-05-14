package cl.duoc.clientes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empresas")
public class Empresa {

    //lucassdc
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empresa_id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(nullable = false, name = "nombre_empresa")
    private String nombreEmpresa;

    @NotBlank(message = "El tipo de empresa no puede estar en blanco")
    @Column(nullable = false, name = "tipo_empresa")
    private String tipoEmpresa;

    @NotBlank(message = "La region no puede estar en blanco")
    @Column(nullable = false)
    private String region;
}
