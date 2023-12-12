# Voting
small projcet on voting created using Jdbc-servlet

## Changes in web.xml
add directory name name in url 
add your user name and password 

## add related tabels 
#table
create table users (id int primary key auto_increment,first_name varchar(20),last_name varchar(20),
email varchar(20) unique,password varchar(20),dob date,status boolean,role varchar(20));

#DML

insert into users values(default,'Rama','Kher','rama@gmail.com','ram#123','1999-1-1',0,'admin');
insert into users values(default,'Shekhar','Patil','shekhar@gmail.com','shk#123','1992-10-20',0,'voter');
insert into users values(default,'Medha', 'Khole','medha@gmail.com','mad$234','1990-11-21',0,'voter');

#candidates table 
create table candidates(id int primary key auto_increment,
name varchar(20) unique,party varchar(20),votes int);

insert into candidates values(default,'ravi','bjp',0);
insert into candidates values(default,'asha','ncp',0);
insert into candidates values(default,'kiran','congress',0);
insert into candidates values(default,'riya','sp',0);
insert into candidates values(default,'subhash','aap',0);

#You can add more candidates under same political party , for party wise , votes 
