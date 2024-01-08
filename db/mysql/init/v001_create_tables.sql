create table if not exists member
(
    created_at       datetime(6) null,
    creator          bigint      null,
    id               bigint auto_increment
    primary key,
    last_modified_at datetime(6) null,
    last_modifier    bigint      null,
    name             varchar(30) null
);

create table if not exists account
(
    balance          bigint      null,
    created_at       datetime(6) null,
    creator          bigint      null,
    id               bigint auto_increment
    primary key,
    last_modified_at datetime(6) null,
    last_modifier    bigint      null,
    member_id        bigint      not null
);

create table if not exists product
(
    id               bigint auto_increment
    primary key,
    created_at       datetime(6) null,
    last_modified_at datetime(6) null,
    creator          bigint      null,
    last_modifier    bigint      null,
    name             varchar(30) null,
    price            int         null,
    quantity         int         null
);

create table if not exists orders
(
    id               bigint auto_increment
    primary key,
    created_at       datetime(6)                               null,
    last_modified_at datetime(6)                               null,
    creator          bigint                                    null,
    last_modifier    bigint                                    null,
    member_id        bigint                                    null,
    status           enum ('REQUEST', 'COMPLETED', 'CANCELED') null
);

create table if not exists order_product
(
    id               bigint auto_increment
    primary key,
    created_at       datetime(6)  null,
    last_modified_at datetime(6)  null,
    creator          bigint       null,
    last_modifier    bigint       null,
    name             varchar(255) null,
    price            int          null,
    product_id       bigint       null,
    quantity         int          null,
    order_id         bigint       null,
    constraint FKl5mnj9n0di7k1v90yxnthkc73
    foreign key (order_id) references orders (id)
);


