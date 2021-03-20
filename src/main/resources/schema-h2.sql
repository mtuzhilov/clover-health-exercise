create table file
(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    date date not null
) charset = utf8;

create table field
(
    id bigint auto_increment primary key,
    file_id bigint not null,
    name varchar(255) not null,
    value_txt text,
    value_bool boolean,
    value_int integer,
    foreign key (file_id) references file(id)
) charset = utf8;
