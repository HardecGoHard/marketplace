create table tag
(
    id         bigserial not null,
    created_at timestamp,
    updated_at timestamp,
    name       varchar(255),
    primary key (id)
);

alter table tag
    add constraint UK_1wdpsed5kna2y38hnbgrnhi5b unique (name)