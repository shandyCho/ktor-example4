create table selling_goods (id varchar(200), goods_name varchar(400), goods_price Integer);
create table user_balance (id uuid, seller_id uuid, proceeds Integer, withdrawal Integer);

alter table selling_goods add column seller_id uuid;