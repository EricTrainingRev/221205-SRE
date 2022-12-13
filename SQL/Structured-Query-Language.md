# SQL
Structured Query Language (sql) is a language used to store, retrieve, and edit information in a database. Many databases will have their own implementation of sql, such as postgreSQL. 

## Postgres
We will be using a Postgres database in training: Postgres is a relational database that has become a popular choice for use over the years. As a relational database, it is easy to create relationships between the contents of your tables by use of primary and foreign keys (discussed below). The data inside a Postgres database is stored in tables, which can be visualized like excel sheets. These tables can be manipulated by sending SQL queries to the database 

## Schema
Postgres makes use of schemas to organize groupped data. For instance, you may want to store your development data inside of a schema called "dev" and have a seperate schema called "production" to store user information. By doing this, any development work you do can target the "dev" schema and you won't accidentally mess with the real data in the "production" schema.

## Table
Data is primarily stored in tables with Postgres: these tables have columns with names to represent the data stored in them, and then each "record" in the table represents an entry of data. For instance, you may have a table called "users" that stores user ids, usernames, and password. There would be three columns for this, one to represent each piece of data for every user.

## Sub-Languages
SQL has different sub languages for performing different kinds of operations: to complete P0 you will need to be familiar with the Data Definition and Data Manipulation sub languages of SQL

## DDL
Data Definition Language (DDL) is the subset of SQL that handles create, editing, and deleting tables from the database. You could also use it to create a schema
```SQL
create table people(
    first_name varchar(20),
    last_name varchar(20),
); 

alter table add column age int; -- this adds a new column after the initial creation

truncate people; -- this removes any records in the table without deleting the table

drop table people; -- this deletes the table and all records from the database

create schema dev

-- the sql below would create a table called people in the dev schema
create table dev.people(
    first_name varchar(20),
    last_name varchar(20),
);
```

## Constraints
Constraints can be placed on tables when they are created or added after creation. These are used to limit what data can/can't be added to the table. A couple of useful constraints to know for P0 the Unique, Not null, Primary Key, and Foreign Key constraints
- Any column with the unique constraint must not share its value with any other record in the same column/table
    - usernames and ids are typically created with the unique constraint, ensuring no two usernames/ids are the same
- Any column with the not null constraint must have some value provided to it: it can not be left blank
- Any column marked as a primary key is given the unique and not null constraints
    - if multiple columns are given the primary key constraint they are called "composite keys"
- Any column marked with the refrence constraint must reference data in the table/column indicated. The column referenced must be marked as a primary key
    - a typical use-case is when you need to indicate ownership
        - a bank with a users and accounts table might make a column called "ownerId" that references the "userId" primary key column in order to indicate what users own what accounts
```SQL
create table primary_table(
    identifier int primary key, -- adds the unique & not null constraints to the column
    uniqueUsername varchar(30) unique, -- adds just the unique constraint to the column
    requiredValue int not null, -- adds just the not null constraint to the column
);

createt table secondary_table(
    identifier int primary key, -- tables can share column names, since they are in different tables
    referencesPrimaryTable int references primary_table(identifier) -- creates a foreign key constraint on the column
);
```


## DML
Data Manipulation Language (DML) is the subset of SQL that handles creating, reading, updating, and deleting information from tables in your database. These subset of actions are typically called CRUD operations. Keep in mind that some people consider SELECT actions to fall under its own sublanguage: Data Query language
```SQL
-- create
insert into people values ('first name','last name'); -- make sure the values match the column positions

select * from people; -- this returns all records in the table

select first_name from people; -- returns all first name recods in the table

select * from people where last_name = 'Wayne'; -- returns all records where the last name column value is Wayne

update people set first_name = 'Bruce' where last_name = 'Wayne' -- sets the first name value to Bruce where last name value is Wayne

delete from people where first_name = 'Robin'; -- deletes all records where first name value is Robin
```

## DCL
If multiple people have access to a database you will most likely not want all of them to have root user privileges: you will want to limit what they can/can't do. This is where Data Control Language (DCL) comes into play. You can create roles that have limited capabilities to protect your database from unwanted actions.
```SQL
-- the query below creates a role called test that has a password of "test" as well
-- the role created below has minimal privileges
create role test NOSUPERUSER NOCREATEDB NOCREATEROLE NOINHERIT login password 'test'; 

grant select on people to test; -- allows anyone logged in with the test role to make select queries on the people table

revoke select on social_security_numbers to test; -- removes the ability to make select queries on the SSN table;
```

## TCL
Any query that makes a change to table data is called a transaction, and you can control these transactions using Transaction Control Language (TCL). Tools like DBeaver handle these commands for you typically, but if you ever want to have your own fine tune control over a query you can manually write them out yourself
```SQL
begin; -- this starts a transaction: nothing below is persisted until and end or commit statement is called
    insert into people values ('Bruce','Banner');
    savepoint bruce_added; -- this creates a savepoint we can roll back to if needed
    insert into people values ('The','Hulk');
    rollback to savepoint bruce_added; -- this rolls the transaction back to where we created the savepoint, so The Hulk's information is not saved
    release savepoint bruce_added; -- it is good practice to release savepoints
end; -- this commits the transaction and persists the changes made
```