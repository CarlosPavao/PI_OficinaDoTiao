create database oficina;
use oficina;
create table acesso(
			login varchar(15) not null,
            senha varchar(8) not null,
            id_acesso int primary key not null auto_increment
);
insert into acesso (login,senha) values ('Alex','alex');
insert into acesso (login,senha) values ('Carlos','carlos');
create table departamento(
			id int primary key not null auto_increment,
            cargo varchar(15) not null
            );
CREATE TABLE endereco (
    id_endereco int PRIMARY KEY not null auto_increment,
    logradouro varchar(30) not null,
    bairro varchar(30) not null,
    numero int not null,
    cep varchar(10) not null,
    cidade varchar(30) not null,
    complemento varchar(30),
    uf varchar(2) not null
);
create table funcionario(
			id_funcionario int primary key not null auto_increment,
            nome_func varchar(40) not null,
            email varchar(25) not null,
            naturalidade varchar(30),
            celular varchar(14) not null,
            rg varchar(11) unique not null,
            data_nasci varchar(10) not null,
            cpf varchar(15) unique not null,
            sexo varchar(10) not null,
            uf_func varchar(2),
            fk_departamento int,
            fk_endereco int,
            foreign key(fk_departamento) references departamento(id),
            foreign key(fk_endereco) references endereco(id_endereco)
            );
alter table funcionario change telefone celular varchar(14) not null;
select * from funcionario;
CREATE TABLE caixa (
    id_venda int PRIMARY KEY not null auto_increment,
    data_venda date not null,
    formas_de_pagamento varchar(10) not null
);
CREATE TABLE produto (
    cod_produto int PRIMARY KEY not null auto_increment,
    valor decimal(9,2) not null,
    desc_produto varchar(30) not null,
    unid_produto varchar(2) not null
);
CREATE TABLE ordem_de_servico (
    id_servico int PRIMARY KEY not null auto_increment,
    valor decimal(7,2) not null,
    problema_identificado varchar(50) not null,
    status_servico varchar(9) not null,
    data_entrada date not null,
    data_saida date not null,
    problema_reclamado varchar(50) not null,
    fk_venda int,
    foreign key(fk_venda) references caixa(id_venda)
);
CREATE TABLE cliente (
    id_cliente int PRIMARY KEY not null auto_increment,
    nome varchar(40) not null,
    sexo varchar(15),
    email varchar(40) not null,
    telefone varchar(15),
    celular varchar(15) not null,
    rg varchar(15) not null,
    cpf varchar(15) unique not null,
    data_nasci varchar(10) not null,
    fk_endereco int,
    foreign key(fk_endereco) references endereco(id_endereco)
);
CREATE TABLE fornecedor (
    id_fornecedor int PRIMARY KEY not null auto_increment,
    nome varchar(40) not null,
    vendedor varchar(20) not null,
    telefone varchar(11)not null,
    fk_endereco int,
    foreign key(fk_endereco) references endereco(id_endereco)
);
CREATE TABLE carro (
    id_car int primary key not null auto_increment,
    placa varchar(9) unique not null,
    modelo varchar(20) not null,
    fabricante varchar(20) not null,
    ano_fab varchar(4) not null,
    cor varchar(15) not null,
    km int not null,
    ano_modelo varchar(4) not null,
    informacoes varchar(50) not null,
    fk_cliente int,
    fk_ordem_de_servico int,
    foreign key(fk_cliente) references cliente(id_cliente),
    foreign key(fk_ordem_de_servico) references ordem_de_servico(id_servico)
);
/* Cadastro de procedure*/
delimiter $$
create procedure novo_cliente (logradouro varchar(30),bairro varchar(30),numero int,cep varchar(10),cidade varchar(30),complemento varchar(30),uf varchar(2),nome varchar(40),sexo varchar(15),email varchar(40),telefone varchar(15),celular varchar(15),rg varchar(15),cpf varchar(15),data_nasci varchar(10),placa varchar(9),modelo varchar(20),fabricante varchar(20),ano_fab varchar(4),cor varchar(15),km int,ano_modelo varchar(4))
begin
insert into endereco (logradouro,bairro,numero,cep,cidade,complemento,uf) values (logradouro,bairro,numero,cep,cidade,complemento,uf);
insert into cliente (nome,sexo,email,telefone,celular,rg,cpf,data_nasci,fk_endereco) values (nome,sexo,email,telefone,celular,rg,cpf,data_nasci,@@identity);
insert into carro (placa,modelo,fabricante,ano_fab,cor,km,ano_modelo,informacoes,fk_cliente) values (placa,modelo,fabricante,ano_fab,cor,km,ano_modelo,informacoes,@@identity);
end$$
delimiter $$
create procedure novo_funcionario (logradouro varchar(30),bairro varchar(30),numero int,cep varchar(10),cidade varchar(30),complemento varchar(30),uf varchar(2),cargo varchar(15),nome_func varchar(40),sexo varchar(15),email varchar(40),telefone varchar(15),naturalidade varchar(30),uf_func varchar(2),rg varchar(15),cpf varchar(15),data_nasci varchar(10),login varchar(9),senha varchar(20))
begin
insert into endereco (logradouro,bairro,numero,cep,cidade,complemento,uf) values (logradouro,bairro,numero,cep,cidade,complemento,uf);
insert into departamento(cargo) value (cargo);
insert into funcionario (nome_func,sexo,email,telefone,naturalidade,uf_func,rg,cpf,data_nasci,fk_endereco,fk_departamento) values (nome_func,sexo,email,telefone,naturalidade,uf_func,rg,cpf,data_nasci,@@identity,@@identity);
insert into acesso (login,senha) values (login,senha);
end$$
select * from endereco;