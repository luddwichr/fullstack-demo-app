create sequence daily_news_message_id_seq start with 1 increment by 1;

create table daily_news_message
(
    id      bigint          not null,
    title   varchar(127)    not null,
    message varchar(255)    not null,
    primary key (id)
);

insert into daily_news_message (id, title, message) values
(nextval('daily_news_message_id_seq'), 'BREAKING', 'this morning, sun rose two minutes earlier than expected!'),
(nextval('daily_news_message_id_seq'), 'URGENT', 'A bag of rice fell over in China! Wow.');