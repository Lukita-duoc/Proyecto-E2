create table inventarios(
    id_inventario bigint auto_increment primary key,
    stock_actual int not null,
    stock_minimo int not null,
    ubicacion varchar(50) not null,
    producto_id bigint not null,
    sucursal_id bigint not null
);