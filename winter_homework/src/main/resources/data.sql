create table web_user(
id serial primary key,
name varchar(50),
password varchar(20)
);
create table commodity(
id serial primary key,
name varchar(50),
com_desc varchar(20),
img_url varchar(20),
old_price float,
new_price float,
stock int,
sales_volume int,
owner_id int
);