create sequence hibernate_sequence start with 1 increment by 1
create table ENTREE_EN_RELATION (id bigint not null, adresse varchar(255), dateCreate timestamp, raisonSociale varchar(255), typeSociete varchar(255), version int4 default '0', primary key (id))
