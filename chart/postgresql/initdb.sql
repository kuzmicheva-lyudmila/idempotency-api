create table if not exists order_details (id varchar(250) primary key, client_id varchar(250), amount bigint, ins_time timestamp default now());
