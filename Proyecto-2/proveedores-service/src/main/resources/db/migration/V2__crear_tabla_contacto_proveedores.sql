CREATE TABLE contacto_proveedores (
                                      id_contacto BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre_contacto VARCHAR(50) NOT NULL,
                                      cargo VARCHAR(50) NOT NULL,
                                      telefono VARCHAR(9) NOT NULL,
                                      id_proveedor BIGINT NOT NULL,
                                      FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);