create table proveedores(
       id_proveedor bigint auto_increment primary key,
       razon_social varchar(50) not null,
       correo varchar(50) not null ,
       telefono varchar(50) not null,
       pais varchar(50) not null ,
       tipo_proveedor not null
);