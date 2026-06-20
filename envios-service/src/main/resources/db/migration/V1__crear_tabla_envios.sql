CREATE TABLE envios (
             id_envio BIGINT AUTO_INCREMENT PRIMARY KEY,
             transportista VARCHAR(100) NOT NULL,
             origen VARCHAR(100) NOT NULL,
             destino VARCHAR(100) NOT NULL,
             fecha_salida DATE NOT NULL,
             estado_envio VARCHAR(50) NOT NULL,
             orden_id BIGINT NOT NULL
);