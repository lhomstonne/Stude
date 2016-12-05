-- drop the existing database
drop database test;

-- create the test user
create user testuser password 'userpass';

-- create the database
create database test owner testuser;

-- ShoppingItems
DROP TABLE SHOPPINGITEMS;
create table SHOPPINGITEMS
(
  ITEMID INTEGER DEFAULT nextval('item_sequence'::text),
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(255),
  PRICE INTEGER,
  constraint PK_SHOPPINGITEMS primary key (ITEMID)
);

DROP SEQUENCE item_sequence;
CREATE SEQUENCE item_sequence INCREMENT 1  MINVALUE 1 CYCLE;

INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Bread','Yummy whole wheat bread',3);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Tomato Soup','Expensive tomato soup',10);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Super Watermelon','This is a magical super watermelon',23498);
