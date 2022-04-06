create table users
(
    username varchar(50)            not null primary key,
    password varchar(2048)          not null
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);

create unique index ix_auth_username on authorities (username, authority);
-- ensure usernames and authorities are really unique (i.e. case insensitive) https://stackoverflow.com/a/814307
create unique index lower_username_idx on users (lower(username));
create unique index lower_authority_idx on authorities (lower(authority));