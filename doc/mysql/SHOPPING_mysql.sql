DROP DATABASE IF EXISTS samples;
CREATE DATABASE samples;
GRANT ALL PRIVILEGES ON samples.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
flush privileges;
use mysql;
update user set host = '%', password=PASSWORD('root') where user='root';
flush privileges;

use samples;
create table SHOPPINGITEMS
(
  ITEMID int unsigned not null auto_increment,
  NAME varchar(50),
  DESCRIPTION varchar(255),
  PRICE int,
  constraint PK_SHOPPINGITEMS primary key (ITEMID)
);

INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Bread','Yummy whole wheat bread',3);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Tomato Soup','Expensive tomato soup',10);
INSERT INTO SHOPPINGITEMS (NAME, DESCRIPTION, PRICE) VALUES ('Super Watermelon','This is a magical super watermelon',23498);
