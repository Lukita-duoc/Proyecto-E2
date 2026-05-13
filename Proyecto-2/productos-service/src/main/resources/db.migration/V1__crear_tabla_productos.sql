create table productos(
    id_producto bigint auto_increment primary key,
    nombre varchar(50) not null,
    descripcion varchar(100) not null,
    precio bigint not null,
    stock bigint not null,
    categoria varchar(50) not null,
    id_marca bigint not null



);