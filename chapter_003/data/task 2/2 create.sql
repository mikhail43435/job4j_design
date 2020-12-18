CREATE TABLE rules(
	id serial primary key,
	name varchar(25)
);
CREATE TABLE "role"(
	id serial primary key,
	name char(25)
);
CREATE TABLE "user"(
	id serial primary key,
	name varchar(25),
	role_id int references role(id)
);
CREATE TABLE "state"(
	id serial primary key,
	name char(25)
);
CREATE TABLE category(
	id serial primary key,
	name varchar(25)
);
CREATE TABLE request(
	id serial primary key,
	name varchar(25),
	role_id int references role(id),
	category_id int references category(id),	
	state_id int references state(id)
);
CREATE TABLE "comments"(
	id serial primary key,
	name varchar(25),
	request_id int references request(id)
);
CREATE TABLE attachs(
	id serial primary key,
	name varchar(25),
	request_id int references request(id)
);
CREATE TABLE role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
)