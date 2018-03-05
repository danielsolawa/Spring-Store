create table activation_token (id bigint not null auto_increment, expire_date tinyblob, token varchar(255), primary key (id)) ENGINE=InnoDB
create table address (id bigint not null auto_increment, city varchar(255), country varchar(255), first_name varchar(255), last_name varchar(255), phone_number varchar(255), street varchar(255), zip_code varchar(255), primary key (id)) ENGINE=InnoDB
create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) ENGINE=InnoDB
create table contact (id bigint not null auto_increment, content varchar(255), conversation_id varchar(255), date tinyblob, message_from varchar(255), subject varchar(255), user_id bigint, primary key (id)) ENGINE=InnoDB
create table contact_user (contact_id bigint not null, user_id bigint not null) ENGINE=InnoDB
create table inventory (id bigint not null auto_increment, user_id bigint, primary key (id)) ENGINE=InnoDB
create table inventory_product (inventory_id bigint not null, product_id bigint not null) ENGINE=InnoDB
create table order_product (order_table_id bigint not null, product_id bigint not null) ENGINE=InnoDB
create table order_table (id bigint not null auto_increment, order_date tinyblob, user_id bigint, primary key (id)) ENGINE=InnoDB
create table product (id bigint not null auto_increment, description varchar(1000), name varchar(255), price double precision, category_id bigint, primary key (id)) ENGINE=InnoDB
create table user (id bigint not null auto_increment, account_non_expired bit not null, account_non_locked bit not null, credentials_non_expired bit not null, enabled bit not null, password varchar(255), role varchar(255), username varchar(255), activation_token_id bigint, address_id bigint, inventory_id bigint, primary key (id)) ENGINE=InnoDB
alter table contact_user add constraint FKpmdm5ukmqc7jaw8dc9i5ywqf7 foreign key (user_id) references user (id)
alter table contact_user add constraint FKeqk244728umgiiwme8vfwex5w foreign key (contact_id) references contact (id)
alter table inventory_product add constraint FK47vtq82lhhheejnn75sundyo6 foreign key (product_id) references product (id)
alter table inventory_product add constraint FKoso19eq22df1t27ievrsq3d69 foreign key (inventory_id) references inventory (id)
alter table order_product add constraint FKhnfgqyjx3i80qoymrssls3kno foreign key (product_id) references product (id)
alter table order_product add constraint FK6rdxcgr3xk8s5se0gcptt6d5b foreign key (order_table_id) references order_table (id)
alter table order_table add constraint FKnmdjo6oaf01ow2reubtrhl6ev foreign key (user_id) references user (id)
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id)
alter table user add constraint FKfyuf7glidvqjxh3t38nwto35s foreign key (activation_token_id) references activation_token (id)
alter table user add constraint FKddefmvbrws3hvl5t0hnnsv8ox foreign key (address_id) references address (id)
alter table user add constraint FKalo4ugar5xpb802kfh7lqlcj4 foreign key (inventory_id) references inventory (id)
