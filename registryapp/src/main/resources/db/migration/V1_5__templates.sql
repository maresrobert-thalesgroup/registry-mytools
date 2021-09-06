create table templates (
       id int4 not null,
        floor_access integer[],
        kit_required varchar(255),
        request_by_id int8,
        request_for_id int8,
        primary key (id)
    );

    alter table templates
           add constraint FKexjfyprd287msioqm4jofosqi
           foreign key (request_by_id)
           references app_user;

    alter table templates
           add constraint FK1kqow5lwhwsvblkb180e9xb7u
           foreign key (request_for_id)
           references app_user;