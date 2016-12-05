create table SHOPPINGITEMS
(
  ITEMID NUMBER not null,
  NAME VARCHAR2(50),
  DESCRIPTION VARCHAR2(255),
  PRICE INT,
  constraint PK_SHOPPINGITEMS primary key (ITEMID)
);

create sequence SHOPPINGITEMS_SEQUENCE start with 1 increment by 1 nomaxvalue;

create trigger SHOPPINGITEMS_ID_TRIGGER
before insert on SHOPPINGITEMS
for each row
begin
select SHOPPINGITEMS_SEQUENCE.nextval into :new.ITEMID from dual;
end;

INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Bread','Yummy whole wheat bread',3);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Tomato Soup','Expensive tomato soup',10);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Super Watermelon','This is a magical super watermelon',23498);
