-- Use this script to setup your Planetarium database

create table users(
	id serial primary key,
	username varchar(20) unique,
	password varchar(20)
);

create table planets(
	id serial primary key,
	name varchar(20),
	ownerId int references users(id)
);

create table moons(
	id serial primary key,
	name varchar(20),
	myPlanetId int references planets(id)
);