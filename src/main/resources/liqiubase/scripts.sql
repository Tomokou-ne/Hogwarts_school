create table faculty
(
    id    bigint NOT NULL generated by default as identity primary key,
    color varchar(255),
    name  varchar(255)
);

create table student
(
    id         bigint NOT NULL generated by default as identity primary key,
    age        integer not null,
    name       varchar(255),
    faculty_id bigint references faculty(id)
);

create table avatar
(
    id         bigint NOT NULL generated by default as identity primary key,
    data       oid,
    file_path  varchar(255),
    file_size  bigint NOT NULL,
    media_type varchar(255),
    student_id bigint references student(id)
);