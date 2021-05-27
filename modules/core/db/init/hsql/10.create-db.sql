-- begin DMMOCKS_PHONE_SETTINGS
create table DMMOCKS_PHONE_SETTINGS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    PHONE_NUMBER varchar(255) not null,
    --
    primary key (ID)
)^
-- end DMMOCKS_PHONE_SETTINGS
