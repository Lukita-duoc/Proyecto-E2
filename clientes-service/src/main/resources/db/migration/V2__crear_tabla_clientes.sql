create table clientes(
    id_cliente bigint auto_increment primary key,
    rut int not null,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    correo varchar(50) not null unique,
    telefono varchar(9) not null,
    empresa_id bigint not null,

    foreign key (empresa_id) references empresas(empresa_id)
);

