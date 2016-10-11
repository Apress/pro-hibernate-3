drop table Publisher
drop table Subscriber
drop sequence hibernate_sequence
create table Publisher (id int4 not null, username varchar(255) unique, primary key (id))
create table Subscriber (id int4 not null, username varchar(255) unique, primary key (id))
create sequence hibernate_sequence
