# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(256) not null,
  name                      varchar(255),
  email                     varchar(256) not null,
  sha_password              varbinary(64) not null,
  constraint uq_user_username unique (username),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

