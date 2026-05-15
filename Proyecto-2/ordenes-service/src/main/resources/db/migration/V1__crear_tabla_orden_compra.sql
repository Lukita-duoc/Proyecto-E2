CREATE TABLE orden_compra (
             id_orden BIGINT AUTO_INCREMENT PRIMARY KEY,
             fecha DATETIME NOT NULL,
             estado VARCHAR(50) NOT NULL,
             total INT NOT NULL,
             cliente_id BIGINT NOT NULL
);