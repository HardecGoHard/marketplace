create table item_tag
(
    item_id int8 not null,
    tag_id  int8 not null,
    primary key (item_id, tag_id)
);

alter table item_tag
    add constraint FKde89ewingaktwyec3nh82pirt
        foreign key (tag_id)
            references tag;

alter table item_tag
    add constraint FKjjb157o07631yt4a1h2fi2i4s
        foreign key (item_id)
            references item;