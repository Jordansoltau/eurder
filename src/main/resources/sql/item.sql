create table item
(
    item_id           varchar not null
        constraint item_id
            primary key,
    item_description  varchar not null,
    item_price        integer not null,
    item_amount_stock integer not null,
    item_name         varchar not null
);