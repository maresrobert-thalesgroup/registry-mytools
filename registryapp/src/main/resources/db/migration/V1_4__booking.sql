create table bookings (
       id int8 not null,
        access_floors integer[],
        end_date timestamp,
        kit_needed varchar(255),
        start_date timestamp,
        status int4 check (status>=0 AND status<=2),
        request_by_id int8,
        request_for_id int8,
        primary key (id)
    );

alter table bookings
       add constraint FKtphvbipvf1tvdaj5a4jtb70rm
       foreign key (request_for_id)
       references app_user;

alter table bookings
       add constraint FKgjkhkhddhhqmox9cxb35vvse8
       foreign key (request_by_id)
       references app_user;