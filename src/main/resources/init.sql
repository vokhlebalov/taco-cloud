create table if not exists Taco_Order
(
    id              INT GENERATED ALWAYS AS IDENTITY,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(2)  not null,
    delivery_Zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null,
    CONSTRAINT Taco_Order_pk PRIMARY KEY (id)
);

create table if not exists Taco
(
    id             INT GENERATED ALWAYS AS IDENTITY,
    name           varchar(50) not null,
    taco_order     bigint      not null REFERENCES Taco_Order (id) ON DELETE CASCADE,
    taco_order_key bigint      not null,
    created_at     timestamp   not null,
    CONSTRAINT Taco_pk PRIMARY KEY (id)
);
create table if not exists Ingredient
(
    id   varchar(4)  not null UNIQUE,
    name varchar(25) not null,
    type varchar(10) not null
);
create table if not exists Ingredient_Ref
(
    ingredient varchar(4) not null REFERENCES Ingredient (id) ON DELETE CASCADE,
    taco       bigint     not null REFERENCES Taco (id) ON DELETE CASCADE,
    taco_key   bigint     not null
);

delete from Ingredient_Ref WHERE TRUE;
delete from Taco WHERE TRUE;
delete from Taco_Order WHERE TRUE;
delete from Ingredient WHERE TRUE;

insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');

insert into Ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE')