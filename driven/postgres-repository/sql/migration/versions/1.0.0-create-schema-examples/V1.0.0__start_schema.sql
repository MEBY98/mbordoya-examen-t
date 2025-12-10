
    create sequence example_child_table_id_seq start with 1 increment by 1

    create sequence example_table_id_seq start with 1 increment by 1

    create sequence module_id_seq start with 1 increment by 1

    create sequence store_id_seq start with 1 increment by 1

    create sequence store_storage_id_seq start with 1 increment by 1

    create table example_child_table (
        example_id bigint,
        id bigint not null,
        child_description varchar(255),
        primary key (id),
        constraint example_child_ukey unique (id, example_id)
    )

    create table example_table (
        id bigint not null,
        type_id bigint,
        name varchar(255),
        primary key (id)
    )

    create table example_type_name_table (
        type_id bigint not null,
        description varchar(255),
        locale_language_code varchar(255) not null,
        primary key (type_id, locale_language_code)
    )

    create table example_type_table (
        type_id bigint not null,
        primary key (type_id)
    )

    create table module (
        capacity integer,
        id bigint not null,
        specialization_id bigint,
        store_id bigint,
        primary key (id)
    )

    create table module_stock (
        quantity integer,
        module_id bigint not null,
        product_id bigint not null,
        primary key (module_id, product_id)
    )

    create table product (
        selling_price float(53),
        id bigint not null,
        specialization_id bigint,
        name varchar(255),
        primary key (id)
    )

    create table specialization (
        id bigint not null,
        primary key (id)
    )

    create table specialization_name (
        id bigint not null,
        locale_language_code varchar(255) not null,
        name varchar(255),
        primary key (id, locale_language_code)
    )

    create table store (
        id bigint not null,
        address varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table store_storage (
        capacity integer,
        id bigint not null,
        store_id bigint,
        name varchar(255),
        primary key (id)
    )

    create table store_storage_stock (
        quantity integer,
        product_id bigint not null,
        store_storage_id bigint not null,
        primary key (product_id, store_storage_id)
    )

    create table user_table (
        password varchar(255),
        username varchar(255) not null,
        primary key (username)
    )

    create index id_example_id_idx
       on example_child_table (id, example_id)

    alter table if exists example_child_table
       add constraint example_child_example_fk
       foreign key (example_id)
       references example_table

    alter table if exists example_table
       add constraint example_example_type_fk
       foreign key (type_id)
       references example_type_table

    alter table if exists example_type_name_table
       add constraint example_type_name_example_type_fk
       foreign key (type_id)
       references example_type_table

    alter table if exists module
       add constraint module_specialization_fk
       foreign key (specialization_id)
       references specialization

    alter table if exists module
       add constraint module_store_fk
       foreign key (store_id)
       references store

    alter table if exists module_stock
       add constraint module_stock_module_fk
       foreign key (module_id)
       references module

    alter table if exists module_stock
       add constraint module_stock_product_fk
       foreign key (product_id)
       references product

    alter table if exists product
       add constraint product_specialization_fk
       foreign key (specialization_id)
       references specialization

    alter table if exists specialization_name
       add constraint specialization_name_specialization_fk
       foreign key (id)
       references specialization

    alter table if exists store_storage
       add constraint store_storage_store_fk
       foreign key (store_id)
       references store

    alter table if exists store_storage_stock
       add constraint store_storage_stock_product_fk
       foreign key (product_id)
       references product

    alter table if exists store_storage_stock
       add constraint store_storage_stock_store_storage_fk
       foreign key (store_storage_id)
       references store_storage