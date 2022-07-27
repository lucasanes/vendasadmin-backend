CREATE DATABASE novosigeve

CREATE SCHEMA sigeve

CREATE TABLE sigeve.usuario
(
    id bigserial NOT NULL PRIMARY KEY,
    nome character varying(150),
    email character varying(100),
    senha character varying(20),
	id_ref bigint,
    data_cadastro date DEFAULT NOW(),
    data_atualizacao date DEFAULT NOW()
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

CREATE TABLE sigeve.empresa
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
	codigo_ref character varying(2),
    data_cadastro date DEFAULT NOW(),
    id_usuario bigint REFERENCES sigeve.usuario (id),
    proximo_numero_nota decimal(10)
)

CREATE TABLE sigeve.nota_entrada
(
    id bigserial NOT NULL PRIMARY KEY,
	numero_nota decimal(10),
    data_nota date,
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

CREATE TABLE sigeve.item_nota_entrada
(
    id bigserial NOT NULL PRIMARY KEY,
    id_nota_entrada bigint REFERENCES sigeve.nota_entrada (id),
    id_produto bigint REFERENCES sigeve.produto (id),
    qtd numeric(9),
    preco numeric(10,2),
    total numeric(10,2)
)