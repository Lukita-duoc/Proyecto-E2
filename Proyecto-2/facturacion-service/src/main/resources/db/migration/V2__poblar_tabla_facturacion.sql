INSERT INTO facturas (fecha_emision, metodo_pago, total, id_cliente, id_orden) VALUES
          (NOW(), 'TARJETA DE DEBITO', 15000, 1, 1),
          (NOW(), 'TRANSFERENCIA', 45000, 2, 2),
          (NOW(), 'TARJETA DE CREDITO', 120000, 3, 3),
          (NOW(), 'EFECTIVO', 7500, 1, 4);