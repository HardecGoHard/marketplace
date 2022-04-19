insert
into users
(id, created_at, updated_at, email, name, password, refresh_code, registration_date, role, status, surname, username)
values (1, current_timestamp, current_timestamp, 'test@email.ru', 'Alex',
        '$2a$10$DKxJlFk3Amrnl32wLNqcue7VBXhT0yLnIQmY./gcugYTKMrX4crUO', '122', current_timestamp, 'USER', 'ACTIVE',
        'Meeen', 'dizing');

insert
into tag
    (id, created_at, updated_at, name)
values (1, current_timestamp, current_timestamp, 'Classical music');

insert
into tag
    (id, created_at, updated_at, name)
values (2, current_timestamp, current_timestamp, 'someTag2');

insert
into item
    (id, created_at, updated_at, description, name, owner_id)
values (1, current_timestamp, current_timestamp, 'description', 'name', 1);

insert
into item_tag
    (item_id, tag_id)
values (1, 1);

insert
into item_tag
    (item_id, tag_id)
values (1, 2);