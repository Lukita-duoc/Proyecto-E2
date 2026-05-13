create table productos(
             id_producto bigint auto_increment primary key,
             nombre varchar(50) not null,
             descripcion varchar(100) not null,
             precio int not null,
             stock int not null,
             categoria varchar(50) not null,
             id_marca bigint not null,

             foreign key (id_marca) references marcas(id_marca)
);