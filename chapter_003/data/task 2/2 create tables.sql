CREATE TABLE rule(
	id serial primary key,
	name varchar(25)
);
CREATE TABLE role(
	id serial primary key,
	name char(25)
);
CREATE TABLE myuser(
	id serial primary key,
	name varchar(25),
	role_id int references role(id)
);
CREATE TABLE req_stat(
	id serial primary key,
	name char(25)
);
CREATE TABLE req_cat(
	id serial primary key,
	name varchar(25)
);
CREATE TABLE request(
	id serial primary key,
	name varchar(25),
	role_id int references role(id),
	req_cat_id int references req_cat(id),	
	req_stat_id int references req_stat(id)
);
CREATE TABLE com(
	id serial primary key,
	name varchar(25),
	request_id int references request(id)
);
CREATE TABLE application(
	id serial primary key,
	name varchar(25),
	request_id int references request(id)
);
CREATE TABLE role_rule(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rule(id)
);