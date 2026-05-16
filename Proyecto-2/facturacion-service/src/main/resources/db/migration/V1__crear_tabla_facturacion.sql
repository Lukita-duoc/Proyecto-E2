CREATE TABLE facturas (
             id_factura BIGINT AUTO_INCREMENT PRIMARY KEY,
             fecha_emision DATETIME NOT NULL,
             metodo_pago VARCHAR(50) NOT NULL,
             total INTEGER NOT NULL,
             id_cliente BIGINT NOT NULL,
             id_orden BIGINT NOT NULL
);