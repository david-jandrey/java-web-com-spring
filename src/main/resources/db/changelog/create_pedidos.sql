--liquibase formatted sql
--changeset david.jandrey:1
CREATE TABLE PEDIDOS (
ID          BIGINT PRIMARY KEY,
DESCRICAO VARCHAR(255),
VALOR_TOTAL FLOAT
);
--rollback DROP PEDIDOS

--changeset david.jandrey:2
COMMENT ON TABLE PEDIDOS IS 'Pedidos';
COMMENT ON COLUMN PEDIDOS.ID IS 'Identificador da pedido';
COMMENT ON COLUMN PEDIDOS.DESCRICAO IS 'Descrição';
COMMENT ON COLUMN PEDIDOS.VALOR_TOTAL IS 'valor total';

--changeset david.jandrey:3
CREATE SEQUENCE SEQ_PEDIDOS;
--rollback DROP SEQ_PEDIDOS