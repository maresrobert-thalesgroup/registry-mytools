create table app_user (
       id int8 not null,
        email varchar(255),
        first_name varchar(255),
        has_office_income_training boolean,
        last_name varchar(255),
        password varchar(255),
        role varchar(255),
        team_id int8,
        primary key (id)
    );

create table gbus (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

create table teams (
       id int8 not null,
       name varchar(255),
       gbu_id int8,
       primary key (id)
    );

alter table app_user
       add constraint FK8mkow04fllrvy2vq04lbmvowp
       foreign key (team_id)
       references teams;


alter table teams
       add constraint FKk4o9dxjd1ibljwgio24bggw2m
       foreign key (gbu_id)
       references gbus;