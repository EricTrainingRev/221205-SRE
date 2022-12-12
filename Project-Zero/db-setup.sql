-- Use this script to setup your Planetarium database

create table users(
	-- because the type of id is set to serial, any time we create a record in this table 
	-- the id value will automatically be set for us
	id serial primary key,
	-- the "primary key" indicator sets a constraint on the column: any column makred
	-- as a primary key must be unique and it must NOT be null
	username varchar(20) unique, -- usernames must be unique because of the unique costraint we added
	password varchar(20)
);

create table planets(
	id serial primary key,
	name varchar(20),
	ownerId int references users(id)
	-- anytime you see "references" you are looking at a "foreign key". All foreign keys
	-- must point to a primary key on another table
);

create table moons(
	id serial primary key,
	name varchar(20),
	myPlanetId int references planets(id)
);