-- Data Control Language is a sublanguage of SQL that lets you create rolls and assign/revoke 
-- their permissions, giving you greater control over who can access and edit information within 
-- your database
create role associate login password 'Revature';

grant select on teams to associate;

revoke select on teams from associate;

-- Transaction Control Language is another sub language of SQL that provides you with
-- fine-tuned control over your quieries that make changes to your tables (insert, update, and)
-- delete queries)

begin; -- starts a transaction
	insert into teams values (default, 'Oregon Ducks');
	savepoint ducks_added; -- this creates a savepoint we can roll back to
	insert into teams values (default, 'Beavers');
	rollback to ducks_added; -- this rolls the transaction back to the indicated savepoint: any
							 -- queries made after the savepoint are not saved
	release savepoint ducks_added;
end; -- commits the transaction

-- date created for join example

create table teams(
	team_id serial primary key,
	team_name varchar(30)
);

create table players(
	player_id serial primary key,
	player_name varchar(30),
	player_team_id int references teams(team_id) on delete set null
);

insert into teams values (default, 'Trailblazers'), (default, 'Seahawks');

insert into players values (default, 'Brandon Roy', 1), (default, 'Marshawn Lynch', 2), (default, 'Greg Oden', 1);

-- joins can be used when you want to view data from two tables in conjunction together

select player_name, team_name -- notice the columns I want are in two different tables
from players left join teams  -- we can use the left join key words to tell postgres we want
							  -- to join the data of the teams table to the player table
on player_team_id = team_id;