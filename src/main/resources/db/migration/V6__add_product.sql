create table product(
    id bigint not null auto_increment primary key,
    description varchar(100),
    product_status enum('new', 'in_stock', 'discontinued'),
    created_date timestamp,
    last_modified_date timestamp
) engine = InnoDB;