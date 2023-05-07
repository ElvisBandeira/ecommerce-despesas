CREATE DATABASE despesa;


CREATE TABLE despesas (
id bigserial PRIMARY KEY,
credor varchar(200) NOT NULL,
data_vencimento date NOT NULL,
data_pagamento date,
valor decimal(10,2) NOT NULL,
descricao varchar(200),
status varchar(100) NOT NULL DEFAULT 'Pendente'
);