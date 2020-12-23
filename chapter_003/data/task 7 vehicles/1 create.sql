create table body (
    id serial primary key,
    name varchar(255)
);
create table gear (
    id serial primary key,
    name varchar(255)
);
create table engine (
    id serial primary key,
    name varchar(255)
);
create table car (
    id serial primary key,
    name varchar(255),
    body_id int references body(id) NOT NULL,
    gear_id int references gear(id) NOT NULL,    
    engine_id int references engine(id) NOT NULL
);