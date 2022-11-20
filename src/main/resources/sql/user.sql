create table "person"
(
    user_id          varchar not null
        constraint user_id
            primary key,
    user_firstname   varchar not null,
    user_lastname    varchar not null,
    user_email       varchar not null,
    user_phonenumber varchar not null,
    user_password    varchar not null,
    user_role        text    not null,
    street           varchar not null,
    housenumber      varchar not null,
    postcode         varchar not null,
    city             varchar not null
);
