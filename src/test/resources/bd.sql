create schema jpa;

create table jpa.persona (
    id int primary key,
    nombre varchar default null,
    apellido varchar default null
);

insert into jpa.persona (id, nombre, apellido) values (1, 'Sebastián', 'Ávila'); 

