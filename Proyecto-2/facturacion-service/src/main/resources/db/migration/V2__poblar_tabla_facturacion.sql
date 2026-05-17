INSERT INTO facturas (fecha_emision, metodo_pago, total, id_cliente, id_orden) VALUES
            (NOW(), 'TARJETA DE DEBITO', 3000000, 1, 1),
            (NOW(), 'TRANSFERENCIA', 1750000, 2, 2),
            (NOW(), 'TARJETA DE CREDITO', 3300000, 3, 3);