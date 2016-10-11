/*
CREATE DATABASE 'hibernate' user 'hibernate' password 'hibernate' default character set ISO8859_1;
CONNECT hibernate user hibernate password hibernate;
IN 'c:\home\sql\chapter13.sql';
*/

create table product (
   id int not null primary key,
   sku int not null unique
);

create table color (
   id int not null primary key,
   name varchar(32) not null unique
);

create table product_color (
   product_id int not null,
   color_id int not null,
   primary key(product_id,color_id)
);
alter table product_color add foreign key (product_id) references product(id);
alter table product_color add foreign key (color_id) references color(id);

create table client (
   id int not null primary key,
   name varchar(32) not null,
   number varchar(10),
   streetname varchar(128),
   town varchar(32),
   city varchar(32),
   country varchar(32)
);

insert into product ( id, sku ) values ( 1, 100001 ); 
insert into product ( id, sku ) values ( 2, 100002 ); 
insert into product ( id, sku ) values ( 3, 100003 ); 

insert into color ( id, name ) values ( 1, 'red' );
insert into color ( id, name ) values ( 2, 'green' );
insert into color ( id, name ) values ( 3, 'blue' );
insert into color ( id, name ) values ( 4, 'puce' );

insert into product_color ( product_id, color_id ) values ( 1, 1 );
insert into product_color ( product_id, color_id ) values ( 1, 2 );
insert into product_color ( product_id, color_id ) values ( 1, 3 );

insert into product_color ( product_id, color_id ) values ( 2, 1 );
insert into product_color ( product_id, color_id ) values ( 2, 2 );
insert into product_color ( product_id, color_id ) values ( 2, 3 );

insert into product_color ( product_id, color_id ) values ( 3, 4 );


insert into client ( id,name,number,streetname,town,city)
	values (1,'David Minter','2000','Chancery Lane','London','London' );

create view vwProduct (ProductKey,ColorKey,Id,SKU,ColorId)
AS 
	select
	   p.id as ProductKey,
	   c.id as ColorKey,
	   p.id as Id,
	   p.sku as SKU,
	   c.id as ColorId
	from
	   product p,
	   product_color pc,
	   color c
	where
	   p.id = pc.product_id
	and
	   pc.color_id = c.id;

SET TERM ^ ;
CREATE PROCEDURE 
   insertClient( p_name varchar(32),
                 p_number varchar(10),
                 p_streetname varchar(128),
                 p_town varchar(32),
                 p_city varchar(32),
                 p_id int)
AS 
BEGIN
   INSERT INTO client 
      (id,name,number,streetname,town,city,country)
   VALUES
      (:p_id,:p_name,:p_number,:p_streetname,:p_town,:p_city,'UK');
END^
SET TERM ; ^

CREATE GENERATOR HIBERNATE_SEQUENCE;