create table sportcars(
	id serial primary key,
	nameof char(25) not null,
	price int not null,
	used boolean not null,
	description text
);
insert into sportcars(nameof, price, used, description) values('Audi', 23, true, 'good condition');
-- update sportcars set nameof = 'BWM';
-- /*delete from sportcars;
-- /*select * from sportcars;