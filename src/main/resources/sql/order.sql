create table "order"
(
    order_id        integer not null
        constraint key_name
            primary key,
    item_id         varchar not null
        constraint foreign_key_name
            references item,
    amount_purchase integer not null,
    shippingdate    date    not null,
    priceoforder    integer not null,
    user_id         varchar not null
        constraint fk_user_id
            references "person"
);