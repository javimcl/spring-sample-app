CREATE TABLE IF NOT EXISTS persona (
   id serial primary key,   
   identificacion varchar(100) NOT NULL,
   nombre varchar(100) NOT NULL,
   apellido varchar(100) NOT NULL);
