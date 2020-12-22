CREATE TABLE subscriber(
	id serial primary key,
	fullname char(25)
);

CREATE TABLE phone_number(
	id serial primary key,
	"number" varchar(25),
	subscriber_id int references subscriber(id)
);
