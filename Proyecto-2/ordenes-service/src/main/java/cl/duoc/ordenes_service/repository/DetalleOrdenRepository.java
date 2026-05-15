package cl.duoc.ordenes_service.repository;

import cl.duoc.ordenes_service.model.DetalleOrden;
import cl.duoc.ordenes_service.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {

    List<DetalleOrden> findByOrdenId(OrdenCompra orden);
}
