create database oficina;

CREATE USER 'oficina'@'localhost' IDENTIFIED BY 'oficina2020';
GRANT ALL PRIVILEGES ON oficina.* TO 'oficina'@'localhost' with grant option;

use oficina;

CREATE TABLE endereco (
    id_endereco int PRIMARY KEY not null auto_increment,
    cep varchar(9) not null,
    logradouro varchar(30) not null,
    numero int not null,
    complemento varchar(30),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf varchar(2) not null
);
create table funcionario(
			id_funcionario int primary key not null auto_increment,
            nome_func varchar(40) not null,
            sexo varchar(10) not null,
            email varchar(25) not null,
            celular varchar(16) not null,
            rg varchar(13) unique not null,
            data_nasci varchar(10) not null,
            cargo varchar(9) not null,
            cpf varchar(14) unique not null,
            senha varchar (8) not null,
            privilegios varchar(10) not null,
            fk_endereco int,
            foreign key(fk_endereco) references endereco(id_endereco)
            );
insert into funcionario (nome_func,sexo,email,celular,rg,data_nasci,cargo,cpf,senha,privilegios) value
						('Tiao','Masculino','tiao@oficinatiao.com.br','(11) 96281-0568','44.258.698.5','04/05/1980','Dono','111.111.111-11','tiao','Dono');
CREATE TABLE caixa (
    id_venda int PRIMARY KEY not null auto_increment,
    data_venda date not null,
    formas_de_pagamento varchar(10) not null
);
CREATE TABLE produto (
    id_produto int PRIMARY KEY not null auto_increment,
    valor decimal(9,2) not null,
    desc_produto varchar(30) not null,
    qtd_estoque varchar(2) not null
);
CREATE TABLE ordem_de_servico (
    id_servico int PRIMARY KEY not null auto_increment,
    subtotal decimal(10,2),
    problema_identificado varchar(50) not null,
    status_servico varchar(9) not null,
    data_entrada timestamp default current_timestamp,
    data_saida date not null,
    qte_pecas int,
    problema_reclamado varchar(150) not null,
    mecanico varchar(50),
    fk_venda int,
    foreign key(fk_venda) references caixa(id_venda)
);
CREATE TABLE cliente (
    id_cliente int PRIMARY KEY not null auto_increment,
    nome varchar(40) not null,
    sexo varchar(15),
    email varchar(40) not null,
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
    vendedor varchar(40) not null,
    telefone varchar(11)not null,
    cnpj varchar(18) not null,
    email varchar(50) not null,
    fk_endereco int,
    foreign key(fk_endereco) references endereco(id_endereco)
);
CREATE TABLE carro (
    id_car int primary key not null auto_increment,
    placa varchar(9) unique not null,
    fabricante varchar(20) not null,
    modelo varchar(20) not null,
    cor varchar(15) not null,    
    km int not null,
    ano_fab varchar(4) not null,
    ano_modelo varchar(4) not null,
    informacoes varchar(50) not null,
    fk_cliente int,
    fk_ordem_de_servico int,
    foreign key(fk_cliente) references cliente(id_cliente),
    foreign key(fk_ordem_de_servico) references ordem_de_servico(id_servico)
);
/* Cadastro de procedure*/
delimiter $$
create procedure novo_cliente (nome_func varchar(40),sexo varchar(10),email varchar(25),celular varchar(16),rg varchar(13),data_nasci varchar(10),cargo varchar(9),cpf varchar(14),senha varchar (8),privilegios varchar(10),cep varchar(9),logradouro varchar(30),numero int,complemento varchar(30),bairro varchar(30),cidade varchar(30),uf varchar(2))
begin
insert into endereco (logradouro,bairro,numero,cep,cidade,complemento,uf) values (logradouro,bairro,numero,cep,cidade,complemento,uf);
insert into cliente (nome,sexo,email,celular,rg,cpf,data_nasci,fk_endereco) values (nome,sexo,email,celular,rg,cpf,data_nasci,@@identity);
insert into carro (placa,modelo,fabricante,ano_fab,cor,km,ano_modelo,informacoes,fk_cliente) values (placa,modelo,fabricante,ano_fab,cor,km,ano_modelo,informacoes,@@identity);
end$$
delimiter $$
create procedure novo_funcionario (nome_func varchar(40),sexo varchar(10),email varchar(25),celular varchar(16),rg varchar(13),data_nasci varchar(10),cargo varchar(9),cpf varchar(14),senha varchar (8),privilegios varchar(10),cep varchar(9),logradouro varchar(30),numero int,complemento varchar(30),bairro varchar(30),cidade varchar(30),uf varchar(2))
begin
insert into endereco (cep,logradouro,numero,bairro,complemento,cidade,uf) values (cep,logradouro,numero,bairro,complemento,cidade,uf);
insert into funcionario (nome_func,sexo,email,celular,rg,data_nasci,cargo,cpf,senha,privilegios,fk_endereco) values (nome_func,sexo,email,celular,rg,data_nasci,cargo,cpf,senha,privilegios,@@identity);
end$$
call novo_funcionario ('Alex','Masculino','alex@hotmail.com','(11) 96521-0586','44.542.142-8','11/11/1981','mecânico','346.529.658-40','alex','Gerente','05588-070','Avenida Joaquim Carvalho',48,'Casa','Monte Kemel','São Paulo','SP');
select * from funcionario;
drop procedure novo_funcionario;

select * from funcionario;

UPDATE funcionario
INNER JOIN endereco ON fk_endereco = endereco_id
 SET  funcionario.privilegios = tabela1.dado1 + tabela2.dado2;