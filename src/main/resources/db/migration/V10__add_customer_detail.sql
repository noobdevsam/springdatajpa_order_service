create table customer (
    id bigint not null auto_increment primary key,
    customer_name varchar(50),
    address varchar(30),
    city varchar(30),
    state varchar(30),
    zip_code varchar(30),
    phone varchar(20),
    email varchar(255),
    created_date timestamp,
    last_modified_date timestamp
);

alter table order_header
    add column customer_id bigint;

alter table order_header
    add constraint order_customer_fk
        foreign key(customer_id) references customer(id);

alter table order_header drop column customer;

insert into customer(customer_name, address, city, state, zip_code, phone, email) values('Customer 1', '124 Dfssf', 'Test city1', 'Test state1', 'Test zip1', '42424532', 'sdkfhkdf@fsfs.com');

update order_header set order_header.customer_id = (select id from customer limit 1);