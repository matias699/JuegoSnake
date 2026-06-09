create database snake;
use snake;

create table usuarios (
    id int primary key auto_increment,
    nombre varchar(100) not null
);

create table puntuaciones (
    id int primary key auto_increment,
    puntuacion int not null,
    fecha date not null,
    id_usuario int,
    foreign key (id_usuario) references usuarios(id)
);

select * from puntuaciones;