create table empleados(
    id_empleado bigint auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    correo varchar(50) not null,
    cargo varchar(50) not null,
    fecha_contrato date not null,
    id_sucursal bigint not null,

    foreign key (id_sucursal) references sucursales(id_sucursal)
);