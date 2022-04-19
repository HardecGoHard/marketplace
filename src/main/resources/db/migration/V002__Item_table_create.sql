create table item
(
    id          bigserial not null,
    created_at  timestamp,
    updated_at  timestamp,
    description varchar(255),
    name        varchar(255),
    owner_id    int8,
    primary key (id)
)