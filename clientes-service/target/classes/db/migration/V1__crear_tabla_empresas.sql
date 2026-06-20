create table empresas(
    empresa_id bigint auto_increment primary key,
    nombre_Empresa varchar(50) not null,
    tipo_empresa varchar(50) not null,
    region varchar(50) not null
);