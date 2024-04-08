CREATE DATABASE novosigeve

CREATE SCHEMA sigeve

SELECT * from sigeve.usuario

INSERT INTO sigeve.usuario (nome, email, senha)
VALUES ('Lucas', 'lucas@gmail.com', 'senha123');

DELETE FROM sigeve.usuario WHERE id = 1;

DROP TABLE sigeve.usuario CASCADE

CREATE TABLE sigeve.usuario
(
    id bigserial NOT NULL PRIMARY KEY,
    nome character varying(150) NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(20) NOT NULL,
    data_cadastro date DEFAULT NOW(),
    data_atualizacao date DEFAULT NOW()
)

SELECT * from sigeve.empresa

INSERT INTO sigeve.empresa (nome, email, senha, id_ref)
VALUES ('Nome do Usuário', 'email@example.com', 'senha123', 1);

DELETE FROM sigeve.empresa WHERE id = 1;

DROP TABLE sigeve.empresa CASCADE

CREATE TABLE sigeve.empresa
(
    id bigserial NOT NULL PRIMARY KEY,
    nome character varying(100) NOT NULL,
    cpf_cnpj character varying(30) NOT NULL,
    inscricao character varying(20) NOT NULL,
    endereco character varying(60) NOT NULL,
	bairro character varying(40) NOT NULL,
	cep character varying(8) NOT NULL,
	cidade character varying(40) NOT NULL,
	uf character varying(2) NOT NULL,
	telefone character varying(20) NOT NULL,
    email  character varying(100) NOT NULL,
	observacao character varying(100),
    proximo_numero_nota decimal(10) NOT NULL,
    data_cadastro date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id) NOT NULL
)

CREATE TABLE sigeve.unidade
(
    id bigserial NOT NULL PRIMARY KEY,
    descricao character varying(30),
	codigo_ref character varying(2),
    data_cadastro date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id)
)

CREATE TABLE sigeve.produto
(
    id bigserial NOT NULL PRIMARY KEY,
    descricao character varying(100),
    id_unidade bigint REFERENCES sigeve.unidade (id),
	codigo_ean character varying(13),
	codigo_ref character varying(6),
    data_cadastro date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id)
)

CREATE TABLE sigeve.fornecedor
(
    id bigserial NOT NULL PRIMARY KEY,
    nome character varying(100),
    cpf_cnpj character varying(30),
    inscricao character varying(20),
    endereco character varying(60),
	bairro character varying(40),
	cep character varying(8),
	cidade character varying(40),
	uf character varying(2),
	telefone character varying(20),
    email  character varying(100),
	observacao character varying(100),
	codigo_ref character varying(6),
    data_cadastro date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id)
)

CREATE TABLE sigeve.nota_fiscal
(
    id bigserial NOT NULL PRIMARY KEY,
	numero_nota decimal(10),
    data_nota date,
    tipo_nota character varying(10),
	id_empresa bigint REFERENCES sigeve.empresa (id),
    id_fornecedor bigint REFERENCES sigeve.fornecedor (id),
    observacao character varying(100),
    valor_total numeric(14,2),
	cancelada int,
    data_cadastro date DEFAULT NOW(),
	data_atualizacao date DEFAULT NOW(),
	data_cancelamento date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id)
)

CREATE TABLE sigeve.item_nota_fiscal
(
    id bigserial NOT NULL PRIMARY KEY,
    id_nota_fiscal bigint REFERENCES sigeve.nota_fiscal (id),
    id_produto bigint REFERENCES sigeve.produto (id),
    qtd numeric(9),
    preco numeric(10,2),
    total numeric(10,2)
)