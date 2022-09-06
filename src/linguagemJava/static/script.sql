create database if not exists estoque;
use estoque;

create or replace table produto (
id int primary key auto_increment,
nomeProduto varchar(150) not null unique,
qtdProduto int not null,
tipoProduto varchar(150) not null,
precoProduto double(9,2) not null,
fornecedor varchar(150) not null,
created_at TIMESTAMP not null default CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
