CREATE DATABASE IF NOT EXISTS demo_test DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

create table dept(deptno int not null primary key auto_increment,dname varchar(60),db_source varchar (60));
insert into dept(dname,db_source) values ('开发部',database());
insert into dept(dname,db_source) values ('人事部',database());
insert into dept(dname,db_source) values ('财务部',database());
insert into dept(dname,db_source) values ('市场部',database());

