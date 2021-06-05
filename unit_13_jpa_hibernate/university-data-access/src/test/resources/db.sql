create table courses (
    id  identity not null primary key,
    course_name varchar(255) not null unique,
);
create table groups (
    id identity not null primary key,
    group_name varchar(255) not null unique,
    course_id  bigint  not null references courses (id),
);
create table teachers (
    id identity not null primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
);
create table groups_teachers (
    teacher_id int8 not null references teachers(id),
    group_id int8 not null references groups(id),
    primary key (teacher_id, group_id)
);
create table topics (
    id identity not null primary key,
    topic_name varchar(255) not null unique
);
create table marks (
    id identity not null primary key,
    mark float8 default 0 not null
);
create table students (
    id identity not null primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    group_id int8 not null references groups(id),
    mark_id int8 not null references  marks(id)
);
create table lessons (
    id identity not null primary key,
    description varchar(255),
    date date not null,
    time time not null,
    group_id int8 not null references groups(id),
    teacher_id int8 not null references teachers(id),
    topic_id int8 references topics(id)
);
insert into courses (course_name) values ('java');
insert into courses (course_name) values ('python');
insert into courses (course_name) values ('js');
insert into groups (group_name, course_id) values ('nix-4', '1');
insert into groups (group_name, course_id) values ('python-alevel-2', '2');
insert into groups (group_name, course_id) values ('2020-js-nix', '3');
insert into topics (topic_name) values ('java reflection');
insert into topics (topic_name) values ('OOP');
insert into topics (topic_name) values ('Strings');
insert into topics (topic_name) values ('Python Abstract classes');
insert into topics (topic_name) values ('Context Manager');
insert into marks (mark) values ('20');
insert into marks (mark) values ('21');
insert into marks (mark) values ('22');
insert into marks (mark) values ('23');
insert into marks (mark) values ('19');
insert into marks (mark) values ('30');
insert into teachers (first_name, last_name) values ('Misha', 'Misha');
insert into teachers (first_name, last_name) values ('Iegor', 'Iegor');
insert into teachers (first_name, last_name) values ('Pasha', 'Pasha');
insert into students (first_name, last_name, group_id, mark_id) values ('Lucy', 'Gorbachova', '1', '6');
insert into students (first_name, last_name, group_id, mark_id) values ('Pasha', 'Cyber', '1', '4');
insert into students (first_name, last_name, group_id, mark_id) values ('Ivan', 'Ivanov', '1', '6');
insert into students (first_name, last_name, group_id, mark_id) values ('Sasha', 'Zinchenko', '2', '1');
insert into students (first_name, last_name, group_id, mark_id) values ('Masha', 'Litvin', '2', '2');
insert into students (first_name, last_name, group_id, mark_id) values ('Alina', 'Kursha', '2', '3');
insert into students (first_name, last_name, group_id, mark_id) values ('Kamal', 'Ahdiev', '3', '5');
insert into students (first_name, last_name, group_id, mark_id) values ('Ivan', 'Surkov', '3', '5');
insert into students (last_name, first_name, group_id, mark_id) values ('Andey', 'Andreev', '3', '6');
insert into lessons (description, date, time,group_id, teacher_id, topic_id ) values ('first lesson', '21.07.2021','15:00:00', '3','3','2');
insert into lessons (description, date, time,group_id, teacher_id, topic_id ) values ('first lesson', '15.06.2021','15:00:00','1' ,'1','2');
insert into lessons (description, date, time,group_id, teacher_id, topic_id ) values ('first lesson continue', '15.06.2021','18:00:00','1' ,'1','2');
insert into lessons (description, date, time,group_id, teacher_id, topic_id ) values (null, '17.06.2021','15:00:00', '1','2','1');
insert into lessons (description, date, time,group_id, teacher_id, topic_id ) values ('context manager 1 lesson', '13.08.2021','15:00:00', '2','3','5');