package cl.duoc.proveedores_service.repository;

import cl.duoc.proveedores_service.model.ContactoProveedor;
import cl.duoc.proveedores_service.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoProveedorRepository extends JpaRepository<ContactoProveedor, Long> {

}
