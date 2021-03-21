create table data_file
(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    imported_at datetime not null
) charset = utf8;

create table data_column
(
    id bigint auto_increment primary key,
    file_id bigint not null,
    name text not null,
    width integer not null,
    type text not null,
    foreign key (file_id) references data_file(id)
) charset = utf8;


create table data_row
(
    id bigint auto_increment primary key,
    column_id bigint not null,
    value_txt text,
    value_bool boolean,
    value_int integer,
    foreign key (column_id) references data_column(id)
) charset = utf8;