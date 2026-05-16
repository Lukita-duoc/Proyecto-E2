package cl.duoc.facturacion_service.repository;

import cl.duoc.facturacion_service.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByIdCliente(Long idCliente);
}
