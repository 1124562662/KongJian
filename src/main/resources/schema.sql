
drop database db_example;
create database db_example;
use db_example;

create table if not exists theUser(
    id bigint AUTO_INCREMENT not null,
    Nickname varchar(100) not null,
    Username varchar(40) not null,
    passWord varchar(200) not null,
    userRole varchar(40) not null,
    primary key (id)
);

create table if not exists article(
    id bigint AUTO_INCREMENT not null,
    createTime  timestamp not null,
    title varchar(100) not null,
    content BLOB,
    ContentType varchar(100) not null,
    primary key (id)
);

create table if not exists comment(
    id bigint AUTO_INCREMENT not null,
    createTime  timestamp not null,
    content  BLOB,
    primary key (id)
);

create table if not exists article_user(
    user_id bigint not null,
    article_id bigint not null
);

create table if not exists comment_user(
   user_id bigint not null,
   comment_id bigint not null
);

create table if not exists comment_article(
    comment_id bigint not null,
    article_id bigint not null
);

alter table article_user add foreign key (user_id) references theUser(id);
alter table article_user add foreign key (article_id) references article(id);

alter table comment_user add foreign key (comment_id) references comment(id);
alter table comment_user add foreign key (user_id) references theUser(id);

alter table comment_article add foreign key (comment_id) references comment(id);
alter table comment_article add foreign key (article_id) references article(id);
