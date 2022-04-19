create table users
(
    id                bigserial not null,
    created_at        timestamp,
    updated_at        timestamp,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    refresh_code      varchar(255),
    registration_date timestamp,
    role              varchar(255),
    status            varchar(255),
    surname           varchar(255),
    username          varchar(255),
    primary key (id)
);

alter table users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);