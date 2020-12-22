insert into devices(name, price) values ('Samsung', 4940);
insert into devices(name, price) values ('Apple', 8456);
insert into devices(name, price) values ('LG', 1794);
insert into devices(name, price) values ('Phillips', 5842);
insert into devices(name, price) values ('Bosch', 542);

insert into people(name) values ('Bill');
insert into people(name) values ('Mike');
insert into people(name) values ('Sam');
insert into people(name) values ('Rol');
insert into people(name) values ('Poll');

insert into devices_people(device_id, people_id) values (11, 12);
insert into devices_people(device_id, people_id) values (11, 13);
insert into devices_people(device_id, people_id) values (12, 15);
insert into devices_people(device_id, people_id) values (12, 11);
insert into devices_people(device_id, people_id) values (13, 13);
insert into devices_people(device_id, people_id) values (13, 14);
insert into devices_people(device_id, people_id) values (14, 11);
insert into devices_people(device_id, people_id) values (14, 12);
insert into devices_people(device_id, people_id) values (14, 13);
insert into devices_people(device_id, people_id) values (14, 14);
insert into devices_people(device_id, people_id) values (15, 12);