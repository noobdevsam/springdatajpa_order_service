alter table order_header
    add column order_status enum('new','in_process','complete');