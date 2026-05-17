create table inventarios(
    id_inventario bigint auto_increment primary key,
    stock_actual integer not null,
    stock_minimo integer not null,
    ubicacion varchar(50) not null,
    producto_id bigint not null,
    sucursal_id bigint not null
);