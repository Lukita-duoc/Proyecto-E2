INSERT INTO orden_compra (fecha, estado, total, cliente_id) VALUES
            (NOW(), 'PENDIENTE', 3000000, 1),
            (NOW(), 'PROCESADO', 1750000, 2),
            (NOW(), 'PENDIENTE', 3300000, 1);