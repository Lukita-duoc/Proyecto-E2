CREATE TABLE detalle_orden (
              id_detalle BIGINT AUTO_INCREMENT PRIMARY KEY,
              cantidad integer NOT NULL,
              subtotal integer NOT NULL,
              id_orden BIGINT NOT NULL,
              id_producto BIGINT NOT NULL,

              FOREIGN KEY (id_orden) REFERENCES orden_compra(id_orden));