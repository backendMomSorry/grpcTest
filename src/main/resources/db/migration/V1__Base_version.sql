create table sales
(
    id              bigserial primary key,
    datetime        timestamptz not null,
    customer_id     bigint      not null,
    points          int         not null,
    sales           numeric     not null,
    payment_method  text        not null,
    last_four_digit text
);

create index sales_customer_id_datetime_ind on sales (customer_id, datetime);