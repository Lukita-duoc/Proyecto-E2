package cl.duoc.proveedores_service.repository;

import cl.duoc.proveedores_service.model.ContactoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoProveedorRepository extends JpaRepository<ContactoProveedor, Long> {
}
