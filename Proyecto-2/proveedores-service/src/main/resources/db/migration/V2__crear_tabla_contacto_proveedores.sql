create table contacto_proveedores(

    id_contacto bigint autoincrement primary key,
    nombreContacto varchar(50) not null,
    cargo varchar(50) not null,
    telefono varchar(9) not null,
    id_proveedor bigint not null,

    foreign key (id_proveedor) references proveedores(id_proveedor)


);