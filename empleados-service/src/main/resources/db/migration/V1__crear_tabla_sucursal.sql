create table sucursales(
    id_sucursal bigint auto_increment primary key,
    nombre varchar(50) not null,
    ciudad varchar(50) not null,
    capacidad int not null
);